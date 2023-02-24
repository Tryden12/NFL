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
import com.tryden.simplenfl.ui.epoxy.EpoxyDataManager
import com.tryden.simplenfl.ui.epoxy.controllers.team.news.TeamNewsEpoxyController
import com.tryden.simplenfl.ui.epoxy.controllers.team.news.TeamNewsTopHeadlinesEpoxyController
import com.tryden.simplenfl.ui.viewmodels.TeamViewModel


class TeamNewsFragment : Fragment() {

    private lateinit var binding: FragmentTeamNewsBinding

    private val viewModel by viewModels<TeamViewModel>()
    private val sharedViewModel: SharedViewModel by activityViewModels()
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


        binding.epoxyTeamNewsHeadlinesRecyclerView.setController(epoxyController)
        epoxyController.setData(emptyList())

        sharedViewModel.onTeamSelectedLiveData.observe(viewLifecycleOwner) { teamId ->
            Log.e("TeamNewsFragment", "teamId: $teamId")
            viewModel.refreshNewsByTeamId(teamId = teamId, "10")
            viewModel.refreshTeam(teamId.toInt())
        }
        viewModel.teamByIdLiveData.observe(viewLifecycleOwner) { team ->
            epoxyDataManager.teamDetails = team
        }
        viewModel.newsByTeamIdLiveData.observe(viewLifecycleOwner) { articleList ->
            val articles: List<ArticleHeadline> = articleList.map { article ->
                viewModel.teamNewsMapper.buildFrom(article!!)
            }
            Log.e("TeamNewsFragment", "items headline: ${articles[0].articleHeadline}")

            val epoxyItems = epoxyDataManager.giveMeTeamNewsEpoxyItems(articles)
            epoxyController.setData(epoxyItems)
        }
    }

    private fun onArticleSelected(articleId: String) {
        Log.e("TeamNewsFragment", "onArticleSelected: $articleId" )

        sharedViewModel.saveCurrentArticleId(articleId = articleId)
        findNavController().navigate(R.id.action_teamFragment_to_articleFragment)
    }
}