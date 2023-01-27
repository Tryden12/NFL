package com.tryden.simplenfl.ui.fragments.team

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.epoxy.controllers.team.news.TeamNewsTopHeadlinesEpoxyController


class TeamNewsFragment : Fragment() {


    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val teamNewsEpoxyController = TeamNewsTopHeadlinesEpoxyController(::onArticleSelected)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyTeamNewsHeadlinesRecyclerView= view.findViewById<EpoxyRecyclerView>(R.id.epoxy_team_news_headlines_RecyclerView)

        sharedViewModel.onTeamSelectedLiveData.observe(viewLifecycleOwner) { teamId ->
            Log.e("TeamNewsFragment", "teamId: $teamId")
            sharedViewModel.refreshNewsByTeamId(teamId = teamId, "20")
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
                teamNewsEpoxyController.maxHeadlines = 5
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