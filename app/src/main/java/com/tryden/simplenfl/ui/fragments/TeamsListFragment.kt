package com.tryden.simplenfl.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.databinding.FragmentTeamsListBinding
import com.tryden.simplenfl.epoxy.controllers.teams.TeamListHomeEpoxyController


class TeamsListFragment : Fragment() {

    private lateinit var binding: FragmentTeamsListBinding

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val epoxyControllerTeamList = TeamListHomeEpoxyController(::onTeamSelected) // function pointer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTeamsListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyTeamListRecyclerView = binding.epoxyTeamListRecyclerView
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