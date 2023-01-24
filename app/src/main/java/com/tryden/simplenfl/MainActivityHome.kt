package com.tryden.simplenfl


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.news.headlines.HomeTopHeadlinesEpoxyController
import com.tryden.simplenfl.teams.TeamListHomeEpoxyController


class MainActivityHome : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

//    private val epoxyControllerTeamList = TeamListHomeEpoxyController(::onTeamSelected) // function pointer

    private val epoxyControllerTopHeadlines = HomeTopHeadlinesEpoxyController()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_home)

//        val epoxyTeamListRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxy_team_list_RecyclerView)
        val epoxyHomeTopHeadlinesRecyclerView= findViewById<EpoxyRecyclerView>(R.id.epoxy_home_top_headlines_RecyclerView)


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

        viewModel.newsBreakingLiveData.observe(this) { response ->

            epoxyControllerTopHeadlines.newsResponse = response
            if (response == null) {
                Toast.makeText(
                    this@MainActivityHome,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

            Log.e(TAG, "onCreate: ${response!!.articles[0].headline}" )
        }

        viewModel.refreshBreakingNews()
//        viewModel.refreshTeamsList()

        epoxyHomeTopHeadlinesRecyclerView.setControllerAndBuildModels(epoxyControllerTopHeadlines)
    //        epoxyTeamListRecyclerView.setControllerAndBuildModels(epoxyControllerTeamList)

    }


//    private fun onTeamSelected(teamId: Int) {
//        Log.e(TAG, "onTeamSelected: $teamId")
//        val intent = Intent(this, MainActivityRoster::class.java)
//        intent.putExtra("test", teamId)
//        startActivity(intent)
//    }


}