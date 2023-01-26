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
import com.tryden.simplenfl.epoxy.controllers.team.roster.TeamRosterEpoxyController

class TeamRosterFragment : Fragment() {


    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyControllerRoster = TeamRosterEpoxyController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_roster, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyRosterRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxy_roster_RecyclerView)

        viewModel.rosterByTeamId.observe(viewLifecycleOwner) { response ->
            epoxyControllerRoster.rosterResponse = response
        }

        viewModel.refreshRoster(2)

        epoxyRosterRecyclerView.setControllerAndBuildModels(epoxyControllerRoster)

    }

}