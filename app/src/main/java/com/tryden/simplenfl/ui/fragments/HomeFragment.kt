package com.tryden.simplenfl.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.epoxy.controllers.news.home.topheadlines.HomeTopHeadlinesEpoxyController
import com.tryden.simplenfl.epoxy.controllers.scores.home.HomeScoresEpoxyController

class HomeFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()


    private val epoxyControllerTopHeadlines = HomeTopHeadlinesEpoxyController(::onArticleSelected)
    private val epoxyControllerScores = HomeScoresEpoxyController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyHomeTopHeadlinesRecyclerView= view.findViewById<EpoxyRecyclerView>(R.id.epoxy_home_top_headlines_RecyclerView)
        val epoxyHomeScoresRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxy_home_scores_RecyclerView)

        sharedViewModel.newsBreakingLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerTopHeadlines.newsResponse = response
            // change the amount of headlines to display
            epoxyControllerTopHeadlines.maxHeadlines = 6

        }
        sharedViewModel.scoreboardByRangeLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerScores.scoresResponse = response

        }
        sharedViewModel.refreshBreakingNews("", "50")
        sharedViewModel.refreshScoreboard("20230114-20230212", "")

        epoxyHomeTopHeadlinesRecyclerView.setControllerAndBuildModels(epoxyControllerTopHeadlines)
        epoxyHomeScoresRecyclerView.setControllerAndBuildModels(epoxyControllerScores)
    }

    private fun onArticleSelected(articleId: String) {
        Log.e("HomeFragment", "onArticleSelected: $articleId" )

        sharedViewModel.saveCurrentArticleId(articleId = articleId)
        findNavController().navigate(R.id.action_homeFragment_to_articleFragment)
    }


}