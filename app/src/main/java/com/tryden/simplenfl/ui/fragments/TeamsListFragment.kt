package com.tryden.simplenfl.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.FragmentTeamsListBinding
import com.tryden.simplenfl.domain.models.teamslist.UiTeam
import com.tryden.simplenfl.ui.epoxy.controllers.teams.TeamListHomeEpoxyController
import com.tryden.simplenfl.ui.viewmodels.TeamsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsListFragment : Fragment(R.layout.fragment_teams_list) {

    private var _binding: FragmentTeamsListBinding? = null
    val binding: FragmentTeamsListBinding get() = _binding!!

    private val epoxyControllerTeamList = TeamListHomeEpoxyController(::onTeamSelected)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTeamsListBinding.bind(view)

        val viewModel by viewModels<TeamsListViewModel>()

        binding.epoxyTeamListRecyclerView.setController(epoxyControllerTeamList)
        epoxyControllerTeamList.setData(emptyList())
        viewModel.teamListLiveData.observe(viewLifecycleOwner) { teamsList ->
            Log.d("TeamLeastFragment()", "Teams list size = ${teamsList.size} ")

            val uiTeams = teamsList.sortedBy { it.name }
            epoxyControllerTeamList.setData(uiTeams)
        }

        viewModel.refreshTeams()
    }

    private fun onTeamSelected(teamId: String) {
        Log.e("TeamsListFragment", "onTeamSelected: $teamId")

        val directions = TeamsListFragmentDirections.actionTeamsListFragmentToTeamFragment(teamId = teamId)
        findNavController().navigate(directions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}