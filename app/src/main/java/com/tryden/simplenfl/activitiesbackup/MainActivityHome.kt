package com.tryden.simplenfl.activitiesbackup


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.epoxy.controllers.news.home.topheadlines.HomeTopHeadlinesEpoxyController
import com.tryden.simplenfl.epoxy.controllers.scores.HomeScoresEpoxyController


class MainActivityHome : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

//    private val epoxyControllerTeamList = TeamListHomeEpoxyController(::onTeamSelected) // function pointer

//    private val epoxyControllerTopHeadlines = HomeTopHeadlinesEpoxyController()
    private val epoxyControllerScores = HomeScoresEpoxyController()

    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_home)
        supportActionBar?.hide()
        setupBottomNav()

        val epoxyHomeTopHeadlinesRecyclerView= findViewById<EpoxyRecyclerView>(R.id.epoxy_home_top_headlines_RecyclerView)
        val epoxyHomeScoresRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxy_home_scores_RecyclerView)


//        viewModel.allTeamsListLiveData.observe(this) { response ->
//
//            epoxyControllerTeamList.teamsListResponse = response
//            if (response == null) {
//                Toast.makeText(
//                    this@MainActivityHome,
//                    "Unsuccessful network call!",
//                    Toast.LENGTH_SHORT
//                ).show()
//                return@observe
//            }
//        }
//
//        viewModel.newsBreakingLiveData.observe(this) { response ->
//            epoxyControllerTopHeadlines.newsResponse = response
//            if (response == null) {
//                Toast.makeText(
//                    this@MainActivityHome,
//                    "Unsuccessful network call!",
//                    Toast.LENGTH_SHORT
//                ).show()
//                return@observe
//            }
//        }
        viewModel.scoreboardByRangeLiveData.observe(this) { response ->
            epoxyControllerScores.scoresHomeResponse = response
            if (response == null) {
                Toast.makeText(
                    this@MainActivityHome,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }

        viewModel.refreshBreakingNews("","")
        viewModel.refreshScoreboard("20230114-20230212", "")
//        viewModel.refreshTeamsList()

//        epoxyHomeTopHeadlinesRecyclerView.setControllerAndBuildModels(epoxyControllerTopHeadlines)
        epoxyHomeScoresRecyclerView.setControllerAndBuildModels(epoxyControllerScores)
    //        epoxyTeamListRecyclerView.setControllerAndBuildModels(epoxyControllerTeamList)

    }


//    private fun onTeamSelected(teamId: Int) {
//        Log.e(TAG, "onTeamSelected: $teamId")
//        val intent = Intent(this, MainActivityRoster::class.java)
//        intent.putExtra("test", teamId)
//        startActivity(intent)
//    }

    private fun setupBottomNav() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    true
                }
                R.id.teamsListFragment -> {
                    true
                }
                R.id.scoresFragment -> {
                    true
                }
                R.id.newsFragment -> {
                    true
                }
                else -> false
            }
        }
    }


}