package com.tryden.simplenfl.ui.fragments.team

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.databinding.FragmentTeamScoresBinding
import com.tryden.simplenfl.domain.models.scores.events.UiEvent
import com.tryden.simplenfl.epoxy.controllers.team.scores.TeamScoresEpoxyController
import com.tryden.simplenfl.ui.viewmodels.ScoresViewModel

class TeamScoresFragment: Fragment(R.layout.fragment_team_scores) {

    private var _binding: FragmentTeamScoresBinding? = null
    val binding: FragmentTeamScoresBinding get() = _binding!!

    private val viewModel by viewModels<ScoresViewModel>()
    private val epoxyControllerScores = TeamScoresEpoxyController()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTeamScoresBinding.bind(view)

        // set team id
        sharedViewModel.onTeamSelectedLiveData.observe(viewLifecycleOwner) { teamId ->
            epoxyControllerScores.onTeamSelected = teamId
        }

        // set data for team selected
        binding.epoxyScoresRecyclerView.setController(epoxyControllerScores)
        epoxyControllerScores.setData(emptyList())
        viewModel.eventListLiveData.observe(viewLifecycleOwner) { eventList ->
            val uiEvents: List<UiEvent> = eventList.map { event ->
                viewModel.uiEventMapper.buildFrom(event)
            }
            epoxyControllerScores.setData(uiEvents)
        }
        viewModel.refreshScores("20220914-20230212", "1000")
    }

}