package com.tryden.simplenfl.ui.fragments.scores

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.adapters.HorizontalWeekMenuAdapter
import com.tryden.simplenfl.databinding.FragmentScoresBinding
import com.tryden.simplenfl.domain.models.scores.Scores
import com.tryden.simplenfl.network.response.teams.models.scores.ScoreboardResponse
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class ScoresFragment: Fragment(R.layout.fragment_scores) {

    private var _binding: FragmentScoresBinding? = null
    val binding: FragmentScoresBinding get() = _binding!!

    private lateinit var weeksMenuAdapter: HorizontalWeekMenuAdapter

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val viewModel by viewModels<ScoresViewModel>()
    private val epoxyControllerScoresByWeek = ScoresByWeekEpoxyController2 { selectedWeek ->

        // todo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentScoresBinding.bind(view)
        setupRecyclerView()

        /** Todo: convert to viewModel, away from sharedViewModel **/
        sharedViewModel.scoresCalendarLiveData.observe(viewLifecycleOwner) { response ->
            // get the weeks list
            val weeks = createWeeksList(response)
            weeksMenuAdapter.differ.submitList(weeks)
            weeksMenuAdapter.notifyDataSetChanged()
        }
        sharedViewModel.refreshScoresCalendar("1") // default to week 1
        /****/

        binding.epoxyScoresByWeekRecyclerView.setController(epoxyControllerScoresByWeek)
        epoxyControllerScoresByWeek.setData(emptyList())
        viewModel.eventListLiveData.observe(viewLifecycleOwner) { eventList ->
            val uiEvents: List<UiEvent> = eventList.map { event ->
                viewModel.uiEventMapper.buildFrom(event)
            }
            epoxyControllerScoresByWeek.setData(uiEvents)
        }
        viewModel.refreshScores("20220908-20220914", "50")
    }

    private fun setupRecyclerView() = binding?.weeksListRecyclerView?.apply {
        weeksMenuAdapter = HorizontalWeekMenuAdapter(::onWeekSelected)
        adapter = weeksMenuAdapter
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun onWeekSelected(range: String) {
        if (range.isNotEmpty()) {
            Log.e("ScoresFragment", "onWeekSelected: $range")

            epoxyControllerScoresByWeek.setData(emptyList())
            viewModel.eventListLiveData.observe(viewLifecycleOwner) { eventList ->
                val uiEvents: List<UiEvent> = eventList.map { event ->
                    viewModel.uiEventMapper.buildFrom(event)
                }
                epoxyControllerScoresByWeek.setData(uiEvents)
            }
            viewModel.refreshScores(range, "50")

        }

    }

    private fun createWeeksList(response: ScoreboardResponse?): MutableList<Scores.EntryWeek> {
        val items = mutableListOf<Scores.EntryWeek>()
        val calendar = response!!.leagues[0].calendar
        if (calendar.isNotEmpty()) {
            for (i in calendar.indices) {
                // Only add the regular season and postseason weeks
                if (calendar[i].label!!.contains("regular", ignoreCase = true)
                    || calendar[i].label!!.contains("postseason", ignoreCase = true)) {
                    for (j in calendar[i].entries!!.indices) {
                        Log.e("HorizontalWeekMenuAdapter", "${calendar[i].entries?.get(j)?.alternateLabel}\n" )

                        calendar[i].entries?.get(j)?.let {
                            items.add(Scores.EntryWeek(
                                label = it.alternateLabel,
                                dates = it.detail,
                                number = it.value,
                                range = getWeekRange(it.startDate, it.endDate)
                            ))
                        }
                    }
                }
            }
        }

        return items
    }

    private fun getWeekRange(startIso: String, endIso: String): String {
        val formatter = DateTimeFormatter.ofPattern("uMMdd")
        val startDateOffSet = OffsetDateTime.parse(startIso, DateTimeFormatter.ISO_DATE_TIME)
        val endDateOffSet = OffsetDateTime.parse(endIso, DateTimeFormatter.ISO_DATE_TIME)

        val start = startDateOffSet.format(formatter)
        val end = endDateOffSet.format(formatter)

        return "$start-$end"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}