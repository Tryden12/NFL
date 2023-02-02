package com.tryden.simplenfl.ui.fragments.team

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.databinding.FragmentTeamNewsBinding
import com.tryden.simplenfl.epoxy.controllers.team.news.TeamNewsTopHeadlinesEpoxyController


class TeamNewsFragment : Fragment() {

    private lateinit var binding: FragmentTeamNewsBinding

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val teamNewsEpoxyController = TeamNewsTopHeadlinesEpoxyController(::onArticleSelected)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentTeamNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyTeamNewsHeadlinesRecyclerView= binding.epoxyTeamNewsHeadlinesRecyclerView
        sharedViewModel.onTeamSelectedLiveData.observe(viewLifecycleOwner) { teamId ->
            Log.e("TeamNewsFragment", "teamId: $teamId")
            sharedViewModel.refreshNewsByTeamId(teamId = teamId, "50")
        }
        sharedViewModel.teamByIdLiveData.observe(viewLifecycleOwner) { response ->
            teamNewsEpoxyController.teamDetailsResponse = response
        }
        sharedViewModel.newsByTeamIdLiveData.observe(viewLifecycleOwner) { response ->
            if (response == null) {
                Toast.makeText(
                    activity,
                    "Unsuccessful response!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
               teamNewsEpoxyController.teamNewsResponse = response
                teamNewsEpoxyController.maxHeadlines = 8
            }
        }
        epoxyTeamNewsHeadlinesRecyclerView.setControllerAndBuildModels(teamNewsEpoxyController)
    }

    private fun onArticleSelected(articleId: String) {
        Log.e("TeamNewsFragment", "onArticleSelected: $articleId" )

        sharedViewModel.saveCurrentArticleId(articleId = articleId)
        findNavController().navigate(R.id.action_teamFragment_to_articleFragment)
    }
}