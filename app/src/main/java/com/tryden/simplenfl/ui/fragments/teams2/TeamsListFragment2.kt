package com.tryden.simplenfl.ui.fragments.teams2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.FragmentTeamsListBinding

class TeamsListFragment2 : Fragment(R.layout.fragment_teams_list) {

    private var _binding: FragmentTeamsListBinding? = null
    val binding: FragmentTeamsListBinding get() = _binding!!

    private val viewModel by viewModels<TeamsListViewModel>()
    private val epoxyControllerTeamList = TeamListHomeEpoxyController2 { selectedTeam ->
        // todo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTeamsListBinding.bind(view)

        binding.epoxyTeamListRecyclerView.setController(epoxyControllerTeamList)
        epoxyControllerTeamList.setData(emptyList())
        viewModel.teamListLiveData.observe(viewLifecycleOwner) { teamsList ->
            val uiTeams: List<UiTeam> = teamsList.map {
                viewModel.uiTeamMapper.buildFrom(it.team)
            }
            epoxyControllerTeamList.setData(uiTeams)
        }

        viewModel.refreshTeams()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}