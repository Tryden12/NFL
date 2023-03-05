package com.tryden.simplenfl.ui.fragments.team

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import com.tryden.simplenfl.R
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.database.entity.FavoriteTeamEntity
import com.tryden.simplenfl.databinding.FragmentTeamBinding
import com.tryden.simplenfl.domain.models.team.Team
import com.tryden.simplenfl.ui.viewmodels.FavoritesViewModel
import com.tryden.simplenfl.ui.viewmodels.TeamViewModel
import com.tryden.simplenfl.ui.viewpager.TeamViewPagerAdapter

class TeamFragment : Fragment() {

    private lateinit var binding: FragmentTeamBinding

    private val teamViewModel by viewModels<TeamViewModel>()
    private val favoritesViewModel by viewModels<FavoritesViewModel>()

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
        binding.backButton.setOnClickListener {
            (activity as AppCompatActivity?)!!.onBackPressed()
        }
    }

    private fun setupComponents() {
        // Refresh team header
        teamViewModel.refreshTeamHeader(teamId = safeArgs.teamId)
        teamViewModel.teamHeaderFlow.asLiveData().observe(viewLifecycleOwner) { teamHeader ->

            val team = teamHeader?.team ?: return@observe

            // Set header colors
            val teamColor = "#${team.color}"
            // status bar
            requireActivity().window.statusBarColor = Color.parseColor(teamColor)

            binding.appBar.background = ColorDrawable(Color.parseColor(teamColor))
            if (team.logo.isEmpty()) {
                binding.logoImageView.visibility = View.GONE
                binding.teamNameTextView.apply {
                    visibility = View.VISIBLE
                    text = team.shortName
                }
            } else {
                binding.teamNameTextView.visibility = View.GONE
                binding.logoImageView.apply {
                    visibility = View.VISIBLE
                    Picasso.get().load(team.logo).into(this)
                }
            }

            // Favorite icon
            val isFavorite = teamHeader.isFavorite
            val imageRes = if (isFavorite) {
                R.drawable.ic_star_24
            } else {
                R.drawable.ic_star_outline_24
            }
            binding.favoriteButton.setIconResource(imageRes)

            binding.favoriteButton.setOnClickListener {
                if (isFavorite) {
                    // Launching the custom alert dialog
                    launchCustomAlertDialog(team) /** delete favorite option within this method **/
                } else {
                    saveFavoriteTeamToDatabase(team)  /** save favorite here **/
                }
            }

            // Tab layout background
            teamTabLayout.setBackgroundColor(Color.parseColor(teamColor))
        }

        // Setup tab layout
        setupTabLayoutAndViewPager()
    }

    @SuppressLint("SetTextI18n")
    private fun launchCustomAlertDialog(team: Team) {
        val builder = AlertDialog.Builder(requireContext(),R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.dialog_alert_unfavorite,null)
        val titleTextView = view.findViewById<AppCompatTextView>(R.id.titleTextView)
        val acceptButton = view.findViewById<MaterialButton>(R.id.acceptDialogButton)
        val cancelButton = view.findViewById<MaterialButton>(R.id.cancelDialogButton)

        builder.setView(view)
        cancelButton.setOnClickListener {
            builder.dismiss()
        }
        acceptButton.setOnClickListener {
            deleteFavoriteTeamFromDatabase(team)
            builder.dismiss()
        }
        titleTextView.text = getString(R.string.are_you_sure) + " ${team.shortName} " + getString(R.string.as_a_favorite)
        builder.show()
    }

    private fun setupTabLayoutAndViewPager() {
        teamAdapter = TeamViewPagerAdapter(childFragmentManager, lifecycle)
        teamViewPager = binding.teamViewPager
        teamTabLayout = binding.teamsTabLayout
        teamViewPager.adapter = teamAdapter

        TabLayoutMediator(teamTabLayout, teamViewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun saveFavoriteTeamToDatabase(team: Team) {

        val favoriteTeamEntity = FavoriteTeamEntity(
            id = team.id,
            shortName = team.shortName,
            longName = team.longName,
            abbreviation = team.abbreviation,
            color = team.color,
            logo = team.logo
        )

        favoritesViewModel.addFavoriteTeam(favoriteTeamEntity)
    }

    private fun deleteFavoriteTeamFromDatabase(team: Team) {

        val favoriteTeamEntity = FavoriteTeamEntity(
            id = team.id,
            shortName = team.shortName,
            longName = team.longName,
            abbreviation = team.abbreviation,
            color = team.color,
            logo = team.logo
        )

        favoritesViewModel.deleteFavoriteTeam(favoriteTeamEntity)
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