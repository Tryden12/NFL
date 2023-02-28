package com.tryden.simplenfl.ui.fragments.team

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.databinding.FragmentTeamNewsBinding
import com.tryden.simplenfl.domain.models.team.ArticleHeadline
import com.tryden.simplenfl.network.response.models.news.Article
import com.tryden.simplenfl.ui.epoxy.EpoxyDataManager
import com.tryden.simplenfl.ui.epoxy.controllers.team.news.TeamNewsEpoxyController
import com.tryden.simplenfl.ui.epoxy.controllers.team.news.TeamNewsTopHeadlinesEpoxyController
import com.tryden.simplenfl.ui.fragments.NewsFragmentDirections
import com.tryden.simplenfl.ui.viewmodels.TeamViewModel


class TeamNewsFragment : Fragment() {

    private lateinit var binding: FragmentTeamNewsBinding

    private val viewModel by viewModels<TeamViewModel>()
    private val epoxyController = TeamNewsEpoxyController(::onArticleSelected)
    private val epoxyDataManager = EpoxyDataManager()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTeamNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set team id
        val teamId = (parentFragment as TeamFragment).getTeamId()
        Log.e("TeamNewsFragment", "teamId: $teamId")
        viewModel.refreshTeam(teamId)
        viewModel.teamByIdLiveData.observe(viewLifecycleOwner) { team ->
            epoxyDataManager.teamDetails = team // todo send to epoxy controller
            epoxyController.logoUrl = team!!.logos[0].href
        }


        binding.epoxyRecyclerView.setController(epoxyController)
        epoxyController.setData(emptyList())

        viewModel.refreshHeadlinesByTeamId(teamId = teamId, limit = "30")
        viewModel.headlinesLiveData.observe(viewLifecycleOwner) { epoxyItems ->
            epoxyController.setData(epoxyItems)
        }
    }

    private fun onArticleSelected(articleId: String) {
        Log.e("TeamNewsFragment", "onArticleSelected: $articleId" )

        val directions = TeamFragmentDirections.actionTeamFragmentToArticleFragment(articleId)
        findNavController().navigate(directions)
    }
}