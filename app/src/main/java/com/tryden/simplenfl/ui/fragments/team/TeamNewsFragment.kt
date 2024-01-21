package com.tryden.simplenfl.ui.fragments.team

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tryden.simplenfl.databinding.FragmentTeamNewsBinding
import com.tryden.simplenfl.ui.epoxy.EpoxyDataManager
import com.tryden.simplenfl.ui.epoxy.controllers.team.news.TeamNewsEpoxyController
import com.tryden.simplenfl.ui.viewmodels.TeamViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel


@AndroidEntryPoint
class TeamNewsFragment : Fragment() {

    private lateinit var binding: FragmentTeamNewsBinding

    private val viewModel by viewModels<TeamViewModel>()
    private val epoxyController = TeamNewsEpoxyController(::onArticleSelected)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTeamNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get team id
        val teamId = (parentFragment as TeamFragment).getTeamId()
        Log.e("TeamNewsFragment", "teamId: $teamId")

        // Get team logo
        viewModel.refreshTeamLogo(teamId = teamId)
        viewModel.teamLogoLiveData.observe(viewLifecycleOwner) { logoUrl ->
            epoxyController.logoUrl = logoUrl
        }


        binding.epoxyRecyclerView.setController(epoxyController)
        epoxyController.setData(emptyList())

        // Get headlines
        viewModel.refreshHeadlinesByTeamId(teamId = teamId, limit = "50")
        viewModel.headlinesLiveData.observe(viewLifecycleOwner) { epoxyItems ->
            epoxyController.setData(epoxyItems)
        }
    }

    private fun onArticleSelected(articleId: String) {
        Log.e("TeamNewsFragment", "onArticleSelected: $articleId" )

        val directions = TeamFragmentDirections.actionTeamFragmentToArticleFragment(articleId)
        findNavController().navigate(directions)
    }
}