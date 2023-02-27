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
        viewModel.refreshNewsByTeamId(teamId = teamId, "50")
        viewModel.refreshTeam(teamId)


        binding.epoxyTeamNewsHeadlinesRecyclerView.setController(epoxyController)
        epoxyController.setData(emptyList())
        viewModel.teamByIdLiveData.observe(viewLifecycleOwner) { team ->
            epoxyDataManager.teamDetails = team
        }
        viewModel.newsByTeamIdLiveData.observe(viewLifecycleOwner) { articleList ->
            val articleListFiltered = articleList.filter { article -> article!!.type == "HeadlineNews" }
            val articles: List<ArticleHeadline> = articleListFiltered.map { article ->
                viewModel.teamNewsMapper.buildFrom(article!!)
            }
            val epoxyItems = epoxyDataManager.giveMeTeamNewsEpoxyItems(articles)
            epoxyController.setData(epoxyItems)
        }
    }

    private fun onArticleSelected(articleId: String) {
        Log.e("TeamNewsFragment", "onArticleSelected: $articleId" )

        val directions = TeamFragmentDirections.actionTeamFragmentToArticleFragment(articleId)
        findNavController().navigate(directions)
    }
}