package com.tryden.simplenfl.ui.fragments.team

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.epoxy.EpoxyRecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.epoxy.controllers.team.header.TeamPageHeaderEpoxyController
import com.tryden.simplenfl.ui.viewpager.TeamViewPagerAdapter

class TeamFragment : Fragment() {

    private var tabTitles = arrayOf("Scores","Roster", "News")

    private val sharedViewModel: SharedViewModel by activityViewModels()


    private val epoxyControllerTeam = TeamPageHeaderEpoxyController()

    private lateinit var teamAdapter: TeamViewPagerAdapter
    private lateinit var teamViewPager: ViewPager2
    private lateinit var teamTabLayout: TabLayout
    private var teamColor: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupComponents()
    }

    private fun setupComponents() {
        val epoxyTeamRecyclerView = view?.findViewById<EpoxyRecyclerView>(R.id.epoxy_team_RecyclerView)

        sharedViewModel.onTeamSelectedLiveData.observe(viewLifecycleOwner) { teamId ->
            sharedViewModel.refreshTeam(teamId = teamId.toInt())
        }
        sharedViewModel.teamByIdLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerTeam.teamResponse = response
            if (response == null) {
                Toast.makeText(
                    activity,
                    "Team response unsuccessful",
                    Toast.LENGTH_SHORT
                ).show()
            }

            // Set header colors
            teamColor = "#${response!!.team.color}"
            // status bar
            requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            requireActivity().window.statusBarColor = getColor(SimpleNFLApplication.context, R.color.black)
//            requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            activity?.window?.setBackgroundDrawable(ColorDrawable(Color.parseColor(teamColor)))
            // header
            epoxyTeamRecyclerView?.background = getTeamColorGradient(teamColor)
            // Setup tab layout
            setupTabLayoutAndViewPager(teamColor)

        }

        epoxyTeamRecyclerView?.setControllerAndBuildModels(epoxyControllerTeam)
    }

    private fun setupTabLayoutAndViewPager(teamColor: String) {
        teamAdapter = TeamViewPagerAdapter(parentFragmentManager, lifecycle)
        teamViewPager = view?.findViewById(R.id.teamViewPager)!!
        teamTabLayout = view?.findViewById(R.id.teamsTabLayout)!!
        teamViewPager?.adapter = teamAdapter

        if (teamTabLayout != null) {
            teamTabLayout.setBackgroundColor(Color.parseColor(teamColor))
            if (teamViewPager != null) {
                TabLayoutMediator(teamTabLayout, teamViewPager) { tab, position ->
                    tab.text = tabTitles[position]
                }.attach()
            }
        }
    }

    private fun getTeamColorGradient(teamColor: String) : GradientDrawable{
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.BOTTOM_TOP,
            intArrayOf(
                Color.parseColor(teamColor),
                getColor(SimpleNFLApplication.context, R.color.black))
        );
        gradientDrawable.cornerRadius = 0f;
        return gradientDrawable
    }

}