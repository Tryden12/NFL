package com.tryden.simplenfl.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.activitiesbackup.MainActivityTeamsList
import com.tryden.simplenfl.epoxy.controllers.team.scores.TeamScoresEpoxyController
import com.tryden.simplenfl.epoxy.controllers.teams.TeamListHomeEpoxyController


class TeamsListFragment : Fragment() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyControllerTeamList = TeamListHomeEpoxyController(::onTeamSelected) // function pointer
    private val epoxyControllerScores = TeamScoresEpoxyController()

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


        viewModel.allTeamsListLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerTeamList.teamsListResponse = response
        }

        viewModel.refreshTeamsList()

        epoxyTeamListRecyclerView.setControllerAndBuildModels(epoxyControllerTeamList)
    }

    private fun onTeamSelected(teamId: Int) {
        Log.e(MainActivityTeamsList.TAG, "onTeamSelected: $teamId")


        val teamFragmentDirections = TeamsListFragmentDirections.actionTeamsListFragmentToTeamFragment(teamId = teamId)
        findNavController().navigate(directions = teamFragmentDirections)

        // todo: try bundling for viewpager fragments

    }

}