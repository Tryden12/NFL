package com.tryden.simplenfl.ui.fragments.team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.databinding.FragmentTeamScoresBinding
import com.tryden.simplenfl.epoxy.controllers.team.scores.TeamScoresEpoxyController

class TeamScoresFragment : Fragment() {

    private lateinit var binding: FragmentTeamScoresBinding

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val epoxyControllerScores = TeamScoresEpoxyController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentTeamScoresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyScoresRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxy_scores_RecyclerView)
        // refresh scoreboard
        sharedViewModel.scoreboardByRangeLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerScores.scoresResponse = response
        }
        sharedViewModel.onTeamSelectedLiveData.observe(viewLifecycleOwner) { teamId ->
            epoxyControllerScores.onTeamSelected = teamId
        }
        sharedViewModel.refreshScoreboard("20220914-20230212","1000")
        epoxyScoresRecyclerView.setControllerAndBuildModels(epoxyControllerScores)

    }
}