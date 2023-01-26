package com.tryden.simplenfl.ui.fragments.team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.epoxy.EpoxyRecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.epoxy.controllers.team.header.TeamPageHeaderEpoxyController
import com.tryden.simplenfl.epoxy.controllers.team.roster.TeamRosterEpoxyController
import com.tryden.simplenfl.epoxy.controllers.team.scores.TeamScoresEpoxyController
import com.tryden.simplenfl.ui.viewpager.TeamViewPagerAdapter

class TeamFragment : Fragment() {

    private var tabTitles = arrayOf("Scores","Roster", "News")

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyControllerTeam = TeamPageHeaderEpoxyController()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabLayoutAndViewPager()
        refreshTeamHeader()

    }

    private fun setupTabLayoutAndViewPager() {
        val teamAdapter = TeamViewPagerAdapter(parentFragmentManager, lifecycle)
        val teamViewPager = view?.findViewById<ViewPager2>(R.id.teamViewPager)
        val teamTabLayout = view?.findViewById<TabLayout>(R.id.teamsTabLayout)
        teamViewPager?.adapter = teamAdapter

        if (teamTabLayout != null) {
            if (teamViewPager != null) {
                TabLayoutMediator(teamTabLayout, teamViewPager) { tab, position ->
                    tab.text = tabTitles[position]
                }.attach()
            }
        }
    }

    private fun refreshTeamHeader() {
        val epoxyTeamRecyclerView = view?.findViewById<EpoxyRecyclerView>(R.id.epoxy_team_RecyclerView)
        viewModel.teamByIdLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerTeam.teamResponse = response
        }
        viewModel.refreshTeam(2)
        epoxyTeamRecyclerView?.setControllerAndBuildModels(epoxyControllerTeam)
    }
}