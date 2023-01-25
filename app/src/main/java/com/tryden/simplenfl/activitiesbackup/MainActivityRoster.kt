package com.tryden.simplenfl.activitiesbackup

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.epoxy.controllers.team.header.TeamPageHeaderEpoxyController
import com.tryden.simplenfl.epoxy.controllers.team.roster.TeamRosterEpoxyController
import com.tryden.simplenfl.epoxy.controllers.team.scores.TeamScoresEpoxyController


class MainActivityRoster : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyControllerTeam = TeamPageHeaderEpoxyController()
    private val epoxyControllerScores = TeamScoresEpoxyController()
    private val epoxyControllerRoster = TeamRosterEpoxyController()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_roster)

        val epoxyTeamRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxy_team_RecyclerView)
        val epoxyRosterRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxy_roster_RecyclerView)

        val view = epoxyTeamRecyclerView.rootView


        viewModel.teamByIdLiveData.observe(this) { response ->

            epoxyControllerTeam.teamResponse = response

            if (response == null) {
                Toast.makeText(
                    this@MainActivityRoster,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

            val teamColor = "#${response.team.color}"


            val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(
                    Color.parseColor(teamColor),
                    getColor(R.color.black))
            );
            gradientDrawable.cornerRadius = 0f;

            //Set Gradient
//            view.background = gradientDrawable;
            epoxyTeamRecyclerView.background = gradientDrawable
            supportActionBar?.apply {
                supportActionBar?.title = ""
                setBackgroundDrawable(ColorDrawable(Color.parseColor(teamColor)))
                elevation = 0f
            }
//            window.statusBarColor = response.team.color.toInt()
//            window.statusBarColor = ContextCompat.getColor(this, response.team.color.toInt())
            window.setBackgroundDrawable(ColorDrawable(Color.parseColor(teamColor)))

        }

        // refresh scoreboard
        viewModel.scoreboardByRangeLiveData.observe(this) { response ->

            epoxyControllerScores.scoresResponse = response

            if (response == null) {
                Toast.makeText(
                    this@MainActivityRoster,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }
        viewModel.rosterByTeamId.observe(this) { response ->
            epoxyControllerRoster.rosterResponse = response

            if (response == null) {
                Toast.makeText(
                    this@MainActivityRoster,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }

        val teamId = intent.getIntExtra("test", 2)
        if (teamId != null) {
            viewModel.refreshTeam(teamId = teamId)
        }
        if (teamId != null) {
            viewModel.refreshRoster(teamId = teamId)
        }

        epoxyTeamRecyclerView.setControllerAndBuildModels(epoxyControllerTeam)
        epoxyRosterRecyclerView.setControllerAndBuildModels(epoxyControllerRoster)

    }


}