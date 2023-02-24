package com.tryden.simplenfl.ui.fragments.team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.databinding.FragmentTeamRosterBinding
import com.tryden.simplenfl.ui.epoxy.controllers.team.roster.TeamRosterEpoxyController

class TeamRosterFragment : Fragment() {

    private lateinit var binding: FragmentTeamRosterBinding

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val epoxyControllerRoster = TeamRosterEpoxyController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTeamRosterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set team id
        val teamId = (parentFragment as TeamFragment).getTeamId()
        sharedViewModel.refreshRoster(teamId = teamId)
        sharedViewModel.rosterByTeamId.observe(viewLifecycleOwner) { response ->
            epoxyControllerRoster.rosterResponse = response
        }

        val epoxyRosterRecyclerView = binding.epoxyRosterRecyclerView
        epoxyRosterRecyclerView.setControllerAndBuildModels(epoxyControllerRoster)
    }

}