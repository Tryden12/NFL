package com.tryden.simplenfl.ui.fragments.team

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.databinding.FragmentTeamScoresBinding
import com.tryden.simplenfl.ui.epoxy.controllers.team.scores.TeamScoresEpoxyController
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEntity
import com.tryden.simplenfl.ui.viewmodels.ScoresViewModel

class TeamScoresFragment: Fragment(R.layout.fragment_team_scores) {

    private var _binding: FragmentTeamScoresBinding? = null
    val binding: FragmentTeamScoresBinding get() = _binding!!

    private val viewModel by viewModels<ScoresViewModel>()
    private val epoxyControllerScores = TeamScoresEpoxyController()
    private val epoxyDataManager = com.tryden.simplenfl.ui.epoxy.EpoxyDataManager()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTeamScoresBinding.bind(view)

        // set team id
        sharedViewModel.onTeamSelectedLiveData.observe(viewLifecycleOwner) { teamId ->
            epoxyDataManager.onTeamSelected = teamId
        }

        // set data for team selected
        binding.epoxyScoresRecyclerView.setController(epoxyControllerScores)
        epoxyControllerScores.setData(emptyList())
        viewModel.eventListLiveData.observe(viewLifecycleOwner) { eventList ->
            val events: List<EventEntity> = eventList.map { event ->
                viewModel.uiEventMapper2.buildFrom(event)
            }
            val epoxyItemsList = epoxyDataManager.giveMeScoresBySeasonTypeEpoxyItems(events)
            epoxyControllerScores.setData(epoxyItemsList)
        }
        viewModel.refreshScores("20220801-20230212", "1000")
    }

}