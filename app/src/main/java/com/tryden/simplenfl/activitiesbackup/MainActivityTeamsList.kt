package com.tryden.simplenfl.activitiesbackup


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.epoxy.controllers.teams.TeamListHomeEpoxyController


class MainActivityTeamsList : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyControllerTeamList = TeamListHomeEpoxyController(::onTeamSelected) // function pointer



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_teams_list)

        val epoxyTeamListRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxy_team_list_RecyclerView)


        viewModel.allTeamsListLiveData.observe(this) { response ->

            epoxyControllerTeamList.teamsListResponse = response
            if (response == null) {
                Toast.makeText(
                    this@MainActivityTeamsList,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }

        viewModel.refreshTeamsList()

        epoxyTeamListRecyclerView.setControllerAndBuildModels(epoxyControllerTeamList)

    }

    private fun onTeamSelected(teamId: Int) {
        Log.e(TAG, "onTeamSelected: $teamId")
        val intent = Intent(this, MainActivityRoster::class.java)
        intent.putExtra("test", teamId)
        startActivity(intent)
    }


}