package com.tryden.simplenfl.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.epoxy.controllers.news.headlines.HomeTopHeadlinesEpoxyController
import com.tryden.simplenfl.epoxy.controllers.scores.HomeScoresEpoxyController

class HomeFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()


    private val epoxyControllerTopHeadlines = HomeTopHeadlinesEpoxyController()
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

        }
        sharedViewModel.scoreboardByRangeLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerScores.scoresHomeResponse = response

        }
        sharedViewModel.refreshBreakingNews()
        sharedViewModel.refreshScoreboard("20230114-20230212", "")

        epoxyHomeTopHeadlinesRecyclerView.setControllerAndBuildModels(epoxyControllerTopHeadlines)
        epoxyHomeScoresRecyclerView.setControllerAndBuildModels(epoxyControllerScores)

    }


}