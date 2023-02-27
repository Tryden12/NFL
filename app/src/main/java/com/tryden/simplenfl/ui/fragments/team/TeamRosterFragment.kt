package com.tryden.simplenfl.ui.fragments.team

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tryden.simplenfl.databinding.FragmentTeamRosterBinding
import com.tryden.simplenfl.ui.epoxy.controllers.team.roster.TeamRosterEpoxyController
import com.tryden.simplenfl.ui.models.RosterViewState
import com.tryden.simplenfl.ui.viewmodels.TeamViewModel

class TeamRosterFragment : Fragment() {

    private lateinit var binding: FragmentTeamRosterBinding

    private val viewModel: TeamViewModel by viewModels()
    private val epoxyController = TeamRosterEpoxyController(::onSelectedSort)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTeamRosterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get team id
        val teamId = (parentFragment as TeamFragment).getTeamId()

        // Epoxy controller setup
        binding.epoxyRosterRecyclerView.setController(epoxyController)
        epoxyController.setData(RosterViewState())
        viewModel.refreshRoster(teamId = teamId)
        viewModel.rosterMapByTeamIdLiveData.observe(viewLifecycleOwner) { rosterMap ->
            Log.e("TeamRosterFragment", "rosterMap size: ${rosterMap.size}" )

            viewModel.currentSort = RosterViewState.Sort.NAME
            onSelectedSort(viewModel.currentSort)
            epoxyController.setData(viewModel.rosterViewStateLiveData.value)
        }
    }

    private fun onSelectedSort(sort: RosterViewState.Sort) {
        viewModel.currentSort = sort

        viewModel.rosterViewStateLiveData.observe(viewLifecycleOwner) { viewState ->
            Log.e("TeamRosterFragment", "onSelectedSort: ${viewModel.currentSort}" )
            epoxyController.setData(viewState)

        }
    }

}