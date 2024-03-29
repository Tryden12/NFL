package com.tryden.simplenfl.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tryden.simplenfl.R
import com.tryden.simplenfl.ui.adapters.HorizontalWeekMenuAdapter
import com.tryden.simplenfl.databinding.FragmentScoresBinding
import com.tryden.simplenfl.domain.models.calendar.UiCalendar
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEntity
import com.tryden.simplenfl.ui.epoxy.EpoxyDataManager
import com.tryden.simplenfl.ui.epoxy.controllers.scores.ScoresByWeekEpoxyController
import com.tryden.simplenfl.ui.viewmodels.ScoresViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScoresFragment: Fragment(R.layout.fragment_scores) {

    private var _binding: FragmentScoresBinding? = null
    val binding: FragmentScoresBinding get() = _binding!!

    private lateinit var weeksMenuAdapter: HorizontalWeekMenuAdapter

    private val viewModel by viewModels<ScoresViewModel>()
    private val epoxyControllerScoresByWeek = ScoresByWeekEpoxyController()
    private val epoxyDataManager = EpoxyDataManager()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentScoresBinding.bind(view)
        setupCalendarRecyclerView()

        viewModel.calendarListLiveData.observe(viewLifecycleOwner) { calendar ->
            // submit weeks to adapter
            val uiWeeks = mutableListOf<UiCalendar.UiWeek>()
            for (i in 0 until calendar.size-1) {
                uiWeeks.addAll(calendar[i].weeks)
            }
            weeksMenuAdapter.differ.submitList(uiWeeks)
            weeksMenuAdapter.notifyDataSetChanged()
        }
        viewModel.refreshCalendar(limit = "1")

        binding.epoxyScoresByWeekRecyclerView.setController(epoxyControllerScoresByWeek)
        epoxyControllerScoresByWeek.setData(emptyList())
        viewModel.eventListLiveData.observe(viewLifecycleOwner) { events ->
            val epoxyItemsList = epoxyDataManager.giveMeScoresByWeekEpoxyItems(events)
            epoxyControllerScoresByWeek.setData(epoxyItemsList)
        }
        // Default loading to HOF Game, todo: load to current week on default
        viewModel.refreshScores(date= "20230801-20230809", limit = "50")
    }

    private fun setupCalendarRecyclerView() = binding?.weeksListRecyclerView?.apply {
        weeksMenuAdapter = HorizontalWeekMenuAdapter(::onWeekSelected)
        adapter = weeksMenuAdapter
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun onWeekSelected(range: String) {
        if (range.isNotEmpty()) {
            Log.e("ScoresFragment", "onWeekSelected: $range")

            epoxyControllerScoresByWeek.setData(emptyList())
            viewModel.eventListLiveData.observe(viewLifecycleOwner) { events ->
                val epoxyItemsList = epoxyDataManager.giveMeScoresByWeekEpoxyItems(events)
                epoxyControllerScoresByWeek.setData(epoxyItemsList)
            }
            viewModel.refreshScores(range, "50")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}