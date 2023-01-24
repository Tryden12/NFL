package com.tryden.simplenfl

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.tryden.simplenfl.team.header.TeamPageHeaderEpoxyController

class MainActivityBackupForTesting : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyControllerTeam = TeamPageHeaderEpoxyController()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_backup_for_testing)

        val teamNameTextView = findViewById<TextView>(R.id.teamNameTextView)
        val recordTextView = findViewById<TextView>(R.id.recordTextView)
        val logoImageView = findViewById<ImageView>(R.id.logoImageView)

        val homeTeamNameTextView = findViewById<TextView>(R.id.teamNameHomeTextview)
        val awayTeamNameTextView = findViewById<TextView>(R.id.teamNameAwayTextview)
        val homeTeamLogoImageView = findViewById<ImageView>(R.id.homeLogoImageView)
        val awayTeamLogoImageView = findViewById<ImageView>(R.id.awayLogoImageView)

        val testAllTeamsTextView = findViewById<TextView>(R.id.testAllTeamsTextView)
        val testRosterByTeamIdTextView = findViewById<TextView>(R.id.testRosterByTeamIdTextView)
        val testBreakingNewsTextView = findViewById<TextView>(R.id.testBreakingNewsTextView)
        val testArticleByIdTextView = findViewById<TextView>(R.id.testArticleByIdTextView)
        val testPlayerByIdTextView = findViewById<TextView>(R.id.testPlayerByIdTextView)

        val view = teamNameTextView.rootView


        // refresh team
        viewModel.refreshTeam(2)
        viewModel.teamByIdLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this@MainActivityBackupForTesting,
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


        // refresh scoreboard
        viewModel.refreshScoreboard("20220908-20230108","1")
        viewModel.scoreboardByRangeLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this@MainActivityBackupForTesting,
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

        // refresh roster
        viewModel.refreshRoster(2)
        viewModel.rosterByTeamId.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this@MainActivityBackupForTesting,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

            val firstPlayerName = response.athletes[0].items[0].fullName
            testRosterByTeamIdTextView.text = firstPlayerName

        }


        // refresh teams list
        viewModel.refreshTeamsList()
        viewModel.allTeamsListLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this@MainActivityBackupForTesting,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

            val body = response.sports
            testAllTeamsTextView.text = body[0].leagues[0].teams[0].team.shortDisplayName
        }

        // refresh breaking news
        viewModel.refreshBreakingNews()
        viewModel.newsBreakingLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this@MainActivityBackupForTesting,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

            val articleHeadline = response.articles[0].headline

            testBreakingNewsTextView.text = articleHeadline

        }

        // refresh article by id
        viewModel.refreshArticle("35475085")
        viewModel.articleById.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this@MainActivityBackupForTesting,
                    "Unsuccessful network call for refreshArticle!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

            val articleByIdHeadline = response.headlines[0].headline
            Log.e(TAG, "onCreate: $articleByIdHeadline", )

            testArticleByIdTextView.text = articleByIdHeadline

        }

        // refresh player by id
        viewModel.refreshPlayer("14876")
        viewModel.playerByIdLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(
                    this@MainActivityBackupForTesting,
                    "Unsuccessful network call for refreshArticle!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

            val playerByIdName = response.displayName
            testPlayerByIdTextView.text = playerByIdName
        }


    }


}