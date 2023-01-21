package com.tryden.simplenfl

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.team.header.TeamPageHeaderEpoxyController
import com.tryden.simplenfl.team.player.PlayerEpoxyController
import com.tryden.simplenfl.team.roster.TeamRosterEpoxyController
import com.tryden.simplenfl.team.scores.TeamScoresEpoxyController


class MainActivityTest : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyControllerTeam = TeamPageHeaderEpoxyController()
    private val epoxyControllerScores = TeamScoresEpoxyController()
    private val epoxyControllerRoster = TeamRosterEpoxyController()
    private val epoxyControllerPlayer = PlayerEpoxyController()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test)

        val epoxyTeamRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxy_team_RecyclerView)
        val epoxyPlayerRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxy_player_RecyclerView)

        val view = epoxyTeamRecyclerView.rootView


        viewModel.teamByIdLiveData.observe(this) { response ->

            epoxyControllerTeam.teamResponse = response

            if (response == null) {
                Toast.makeText(
                    this@MainActivityTest,
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
                    this@MainActivityTest,
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
                    this@MainActivityTest,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }
        viewModel.playerByIdLiveData.observe(this) { response ->
            epoxyControllerPlayer.playerResponse = response

            if (response == null) {
                Toast.makeText(
                    this@MainActivityTest,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }

        viewModel.refreshTeam(10)
        viewModel.refreshPlayer("14876")

        epoxyTeamRecyclerView.setControllerAndBuildModels(epoxyControllerTeam)
        epoxyPlayerRecyclerView.setControllerAndBuildModels(epoxyControllerPlayer)

    }


}