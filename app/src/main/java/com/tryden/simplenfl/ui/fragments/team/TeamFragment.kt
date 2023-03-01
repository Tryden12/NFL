package com.tryden.simplenfl.ui.fragments.team

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import com.tryden.simplenfl.R
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.databinding.FragmentTeamBinding
import com.tryden.simplenfl.ui.viewmodels.TeamViewModel
import com.tryden.simplenfl.ui.viewpager.TeamViewPagerAdapter

class TeamFragment : Fragment() {

    private lateinit var binding: FragmentTeamBinding

    private val viewModel by viewModels<TeamViewModel>()

    private var tabTitles = arrayOf("Scores","Roster", "News")

    private lateinit var teamAdapter: TeamViewPagerAdapter
    private lateinit var teamViewPager: ViewPager2
    private lateinit var teamTabLayout: TabLayout

    private val safeArgs: TeamFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTeamBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topToolbarSetup()
        setupComponents()
    }

    private fun topToolbarSetup() {
        val toolbar = binding.topMenuMaterialToolbar
        toolbar.setNavigationOnClickListener {
            (activity as AppCompatActivity?)!!.onBackPressed()
        }
    }

    private fun setupComponents() {
        // Refresh team header
        viewModel.refreshTeamHeader(teamId = safeArgs.teamId)
        viewModel.teamHeaderLiveData.observe(viewLifecycleOwner) { team ->
            // Set header colors
            val teamColor = "#${team!!.color}"
            // status bar
//            requireActivity().window.statusBarColor = getColor(SimpleNFLApplication.context, R.color.black)
            requireActivity().window.statusBarColor = Color.parseColor(teamColor)

            binding.appBar.background = ColorDrawable(Color.parseColor(teamColor))
            if (team.logo.isEmpty()) {
                Picasso.get()
                    .load(R.drawable.placeholder_logo)
                    .placeholder(R.drawable.placeholder_logo)
                    .error(R.drawable.placeholder_logo)
                    .into(binding.logoImageView)
            } else {
                Picasso.get().load(team.logo).into(binding.logoImageView)
            }
//            binding.teamNameTextView.text = team.shortName

            // Setup tab layout
            setupTabLayoutAndViewPager(teamColor)
        }
    }

    private fun setupTabLayoutAndViewPager(teamColor: String) {
        teamAdapter = TeamViewPagerAdapter(childFragmentManager, lifecycle)
        teamViewPager = binding.teamViewPager
        teamTabLayout = binding.teamsTabLayout
        teamViewPager.adapter = teamAdapter

        teamTabLayout.setBackgroundColor(Color.parseColor(teamColor))
        TabLayoutMediator(teamTabLayout, teamViewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
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

    fun getTeamId() = safeArgs.teamId

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().window.statusBarColor = getColor(SimpleNFLApplication.context, R.color.black)
    }

}