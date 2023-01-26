package com.tryden.simplenfl.ui.fragments.team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.epoxy.controllers.team.scores.TeamScoresEpoxyController

class TeamScoresFragment : Fragment() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyControllerScores = TeamScoresEpoxyController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_scores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyScoresRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxy_scores_RecyclerView)

        // refresh scoreboard
        viewModel.scoreboardByRangeLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerScores.scoresResponse = response
        }
        viewModel.refreshScoreboard("20220914-20230212","1000")

        epoxyScoresRecyclerView.setControllerAndBuildModels(epoxyControllerScores)

    }
}