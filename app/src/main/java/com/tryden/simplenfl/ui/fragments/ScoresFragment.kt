package com.tryden.simplenfl.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.adapters.HorizontalWeekMenuAdapter
import com.tryden.simplenfl.databinding.FragmentScoresBinding
import com.tryden.simplenfl.domain.models.scores.Scores.EntryWeek
import com.tryden.simplenfl.domain.models.scoresmenu.Week
import com.tryden.simplenfl.epoxy.controllers.scores.ScoresByWeekEpoxyController
import com.tryden.simplenfl.network.response.teams.models.scores.ScoreboardResponse
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class ScoresFragment : Fragment() {

    private var binding: FragmentScoresBinding? = null


    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val epoxyControllerScores = ScoresByWeekEpoxyController(::onWeekSelected)

    private lateinit var weeksMenuAdapter: HorizontalWeekMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_scores, container, false)
        binding = FragmentScoresBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        val epoxyScoresRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxy_scores_by_week_RecyclerView)
        // refresh scoreboard
        sharedViewModel.scoresCalendarLiveData.observe(viewLifecycleOwner) { response ->
//            epoxyControllerScores.calendarResponse = response
//            epoxyControllerScores.useCurrentTimeIso = true

            // get the weeks list
            val weeks = createWeeksList(response)

            weeksMenuAdapter.differ.submitList(weeks)
            weeksMenuAdapter.notifyDataSetChanged()

        }
        sharedViewModel.refreshScoresCalendar("1")

        // refresh scoreboard
        sharedViewModel.scoreboardByRangeLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerScores.scoresByWeekResponse = response

        }
        sharedViewModel.refreshScoreboard("20230208-20230215", "1000")
        epoxyScoresRecyclerView.setControllerAndBuildModels(epoxyControllerScores)

//        Log.e("ScoresFragment", "getCurrentWeek: ${getCurrentTimeIso()}")

    }

    private fun setupRecyclerView() = binding?.weeksListRecyclerView?.apply {
        weeksMenuAdapter = HorizontalWeekMenuAdapter(::onWeekSelected)
        adapter = weeksMenuAdapter
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }


    private fun onWeekSelected(range: String) {
        if (range.isNotEmpty()) {
            Log.e("ScoresFragment", "onWeekSelected: $range")

            // refresh scoreboard
            sharedViewModel.scoreboardByRangeLiveData.observe(viewLifecycleOwner) { response ->
                epoxyControllerScores.scoresByWeekResponse = response

            }
            sharedViewModel.refreshScoreboard(range, "1000")

            val epoxyScoresRecyclerView = view?.findViewById<EpoxyRecyclerView>(R.id.epoxy_scores_by_week_RecyclerView)
            epoxyScoresRecyclerView?.setControllerAndBuildModels(epoxyControllerScores)
        }

    }

    private fun createWeeksList(response: ScoreboardResponse?): MutableList<EntryWeek> {
        val items = mutableListOf<EntryWeek>()
        val calendar = response!!.leagues[0].calendar
        if (calendar.isNotEmpty()) {
            for (i in calendar.indices) {
                // Only add the regular season and postseason weeks
                if (calendar[i].label!!.contains("regular", ignoreCase = true)
                    || calendar[i].label!!.contains("postseason", ignoreCase = true)) {
                    for (j in calendar[i].entries!!.indices) {
                        Log.e("HorizontalWeekMenuAdapter", "${calendar[i].entries?.get(j)?.alternateLabel}\n" )

                        calendar[i].entries?.get(j)?.let {
                            items.add(EntryWeek(
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

    fun getWeekRange(startIso: String, endIso: String): String {
        val formatter = DateTimeFormatter.ofPattern("uMMdd")
        val startDateOffSet = OffsetDateTime.parse(startIso, DateTimeFormatter.ISO_DATE_TIME)
        val endDateOffSet = OffsetDateTime.parse(endIso, DateTimeFormatter.ISO_DATE_TIME)

        val start = startDateOffSet.format(formatter)
        val end = endDateOffSet.format(formatter)


        return "$start-$end"
    }

    private fun getCurrentTimeIso(): OffsetDateTime {
        val currentTime = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME)
        return OffsetDateTime.parse(currentTime, DateTimeFormatter.ISO_DATE_TIME)

    }
}