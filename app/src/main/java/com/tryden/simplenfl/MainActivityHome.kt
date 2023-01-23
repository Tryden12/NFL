package com.tryden.simplenfl


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.teams.TeamListHomeEpoxyController


class MainActivityHome : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyControllerTeamList = TeamListHomeEpoxyController()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_home)

        val epoxyTeamListRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxy_team_list_RecyclerView)


        viewModel.allTeamsListLiveData.observe(this) { response ->

            epoxyControllerTeamList.teamsListResponse = response
            if (response == null) {
                Toast.makeText(
                    this@MainActivityHome,
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }

        viewModel.refreshTeamsList()

        epoxyTeamListRecyclerView.setControllerAndBuildModels(epoxyControllerTeamList)

    }


}