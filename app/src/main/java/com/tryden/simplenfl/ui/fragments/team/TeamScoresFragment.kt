package com.tryden.simplenfl.ui.fragments.team

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.FragmentTeamScoresBinding
import com.tryden.simplenfl.ui.epoxy.controllers.team.scores.TeamScoresEpoxyController
import com.tryden.simplenfl.ui.epoxy.interfaces.events.EventEntity
import com.tryden.simplenfl.ui.viewmodels.ScoresViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamScoresFragment: Fragment(R.layout.fragment_team_scores) {

    private var _binding: FragmentTeamScoresBinding? = null
    val binding: FragmentTeamScoresBinding get() = _binding!!

    private val viewModel by viewModels<ScoresViewModel>()
    private val epoxyControllerScores = TeamScoresEpoxyController()
    private val epoxyDataManager = com.tryden.simplenfl.ui.epoxy.EpoxyDataManager()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTeamScoresBinding.bind(view)

        // set team id
        val teamId = (parentFragment as TeamFragment).getTeamId()
        epoxyDataManager.onTeamSelected = teamId

        // set data for team selected
        binding.epoxyScoresRecyclerView.setController(epoxyControllerScores)
        epoxyControllerScores.setData(emptyList())
        viewModel.eventListLiveData.observe(viewLifecycleOwner) { events ->
            Log.d("TeamScoresFragment()", "eventList = ${events.size}")
            val epoxyItemsList = epoxyDataManager.giveMeScoresBySeasonTypeEpoxyItems(events)
            epoxyControllerScores.setData(epoxyItemsList)
        }
        viewModel.refreshScores("20230801-20240212", "1000")
    }

}