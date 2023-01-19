package com.tryden.simplenfl

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.team.header.TeamPageHeaderEpoxyController

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyControllerTeam = TeamPageHeaderEpoxyController()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = ""
        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxy_team_RecyclerView)

        val view = epoxyRecyclerView.rootView


        viewModel.teamByIdLiveData.observe(this) { response ->

            epoxyControllerTeam.teamResponse = response

            if (response == null) {
                Toast.makeText(
                    this@MainActivity,
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
            view.background = gradientDrawable;

        }
        // refresh team
        viewModel.refreshTeam(2)

        epoxyRecyclerView.setControllerAndBuildModels(epoxyControllerTeam)


    }


}