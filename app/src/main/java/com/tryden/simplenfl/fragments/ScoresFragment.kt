package com.tryden.simplenfl.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.epoxy.controllers.team.header.TeamPageHeaderEpoxyController
import com.tryden.simplenfl.epoxy.controllers.team.scores.TeamScoresEpoxyController


class ScoresFragment : Fragment() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyControllerTeam = TeamPageHeaderEpoxyController()
    private val epoxyControllerScores = TeamScoresEpoxyController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyTeamRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxy_team_RecyclerView)
        val epoxyScoresRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxy_scores_RecyclerView)
        viewModel.teamByIdLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerTeam.teamResponse = response
        }

        // refresh scoreboard
        viewModel.scoreboardByRangeLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerScores.scoresResponse = response
        }

        viewModel.refreshTeam(2)
        viewModel.refreshScoreboard("20220914-20230212","1000")

        epoxyTeamRecyclerView.setControllerAndBuildModels(epoxyControllerTeam)
        epoxyScoresRecyclerView.setControllerAndBuildModels(epoxyControllerScores)

    }
}