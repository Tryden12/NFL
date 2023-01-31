package com.tryden.simplenfl.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.epoxy.controllers.scores.ScoresByWeekEpoxyController
import com.tryden.simplenfl.epoxy.controllers.scores.home.HomeScoresEpoxyController
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class ScoresFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()


    private val epoxyControllerScores = ScoresByWeekEpoxyController(::onWeekSelected)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val epoxyScoresRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxy_scores_by_week_RecyclerView)
        // refresh scoreboard
        sharedViewModel.scoresCalendarLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerScores.calendarResponse = response
            epoxyControllerScores.useCurrentTimeIso = true
        }
        sharedViewModel.refreshScoresCalendar("1")

        sharedViewModel.scoreboardByRangeLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerScores.scoresByWeekResponse = response
        }
        sharedViewModel.refreshScoreboard("20221215-20230109", "1000")
        epoxyScoresRecyclerView.setControllerAndBuildModels(epoxyControllerScores)

//        Log.e("ScoresFragment", "getCurrentWeek: ${getCurrentTimeIso()}")

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

    private fun getCurrentTimeIso(): OffsetDateTime {
        val currentTime = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME)
        return OffsetDateTime.parse(currentTime, DateTimeFormatter.ISO_DATE_TIME)

    }
}