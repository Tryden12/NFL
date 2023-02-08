package com.tryden.simplenfl.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.databinding.FragmentHomeBinding
import com.tryden.simplenfl.domain.models.scores.events.UiEvent
import com.tryden.simplenfl.epoxy.controllers.news.home.topheadlines.HomeTopHeadlinesEpoxyController
import com.tryden.simplenfl.epoxy.controllers.scores.ScoresUpcomingEpoxyController
import com.tryden.simplenfl.ui.viewmodels.ScoresViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    val binding: FragmentHomeBinding get() = _binding!!

    private val viewModel by viewModels<ScoresViewModel>()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val epoxyControllerTopHeadlines = HomeTopHeadlinesEpoxyController(::onArticleSelected)
    private val epoxyControllerScoresUpcoming = ScoresUpcomingEpoxyController()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        val epoxyHomeTopHeadlinesRecyclerView = binding.epoxyHomeTopHeadlinesRecyclerView

        sharedViewModel.newsBreakingLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerTopHeadlines.newsResponse = response
            // change the amount of headlines to display
            epoxyControllerTopHeadlines.maxHeadlines = 6
        }
        sharedViewModel.refreshBreakingNews("", "50")
        epoxyHomeTopHeadlinesRecyclerView.setControllerAndBuildModels(epoxyControllerTopHeadlines)

        binding.epoxyHomeScoresRecyclerView.setController(epoxyControllerScoresUpcoming)
        epoxyControllerScoresUpcoming.setData(emptyList())
        viewModel.eventListLiveData.observe(viewLifecycleOwner) { eventList ->
            val uiEvents: List<UiEvent> = eventList.map { event ->
                viewModel.uiEventMapper.buildFrom(event)
            }
            epoxyControllerScoresUpcoming.setData(uiEvents)
        }
        // Default loading to hard coded week, todo: load to current week on default
        viewModel.refreshScores(date= "20230128-20230215", limit = "5")
    }

    private fun onArticleSelected(articleId: String) {
        Log.e("HomeFragment", "onArticleSelected: $articleId" )

        sharedViewModel.saveCurrentArticleId(articleId = articleId)
        findNavController().navigate(R.id.action_homeFragment_to_articleFragment)
    }


}