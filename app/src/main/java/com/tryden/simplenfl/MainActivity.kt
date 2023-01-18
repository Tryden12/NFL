package com.tryden.simplenfl

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val teamNameTextView = findViewById<TextView>(R.id.teamNameTextView)
        val recordTextView = findViewById<TextView>(R.id.recordTextView)
        val logoImageView = findViewById<ImageView>(R.id.logoImageView)

        val homeTeamNameTextView = findViewById<TextView>(R.id.teamNameHomeGameItemTextview)
        val awayTeamNameTextView = findViewById<TextView>(R.id.teamNameAwayGameItemTextview)
        val homeTeamLogoImageView = findViewById<ImageView>(R.id.homeLogoImageView)
        val awayTeamLogoImageView = findViewById<ImageView>(R.id.awayLogoImageView)

        val view = teamNameTextView.rootView


        viewModel.refreshScoreboard()
        viewModel.scoreboardByRangeLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

            val scores = response.events
            val homeTeamName = scores[0].competitions[0].competitors[0].team.name
            val awayTeamName = scores[0].competitions[0].competitors[1].team.name

            val homeTeamLogo = scores[0].competitions[0].competitors[0].team.logo
            val awayTeamLogo = scores[0].competitions[0].competitors[1].team.logo

            homeTeamNameTextView.text = homeTeamName
            awayTeamNameTextView.text = awayTeamName
            Picasso.get().load(homeTeamLogo).into(homeTeamLogoImageView)
            Picasso.get().load(awayTeamLogo).into(awayTeamLogoImageView)


        }
        viewModel.refreshTeam(2)
        viewModel.teamByIdLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
            val team = response.team
            val teamName = team.displayName
            val record = team.record.items[0].summary
            val logo = team.logos[1].href
            val teamColor = "#${team.color}"
            val alternateColor = "#${team.alternateColor}"

            teamNameTextView.text = teamName
            recordTextView.text = "(${record})"

            Picasso.get().load(logo).into(logoImageView)

            val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(
                    Color.parseColor(teamColor),
                    getColor(R.color.black))
                );
            gradientDrawable.cornerRadius = 0f;

            //Set Gradient
            view.background = gradientDrawable;
            supportActionBar?.title = ""
        }

    }

}