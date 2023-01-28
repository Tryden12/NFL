package com.tryden.simplenfl.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.epoxy.controllers.teams.TeamListHomeEpoxyController


class TeamsListFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val epoxyControllerTeamList = TeamListHomeEpoxyController(::onTeamSelected) // function pointer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val epoxyTeamListRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxy_team_list_RecyclerView)
        sharedViewModel.allTeamsListLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerTeamList.teamsListResponse = response
        }
        sharedViewModel.refreshTeamsList()
        epoxyTeamListRecyclerView.setControllerAndBuildModels(epoxyControllerTeamList)
    }

    private fun onTeamSelected(teamId: Int) {
        Log.e("TeamsListFragment", "onTeamSelected: $teamId")

        sharedViewModel.saveCurrentTeamId(teamId = teamId.toString())
        findNavController().navigate(R.id.action_teamsListFragment_to_teamFragment)
    }

}