package com.tryden.simplenfl

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val view = teamNameTextView.rootView


        viewModel.refreshTeam(10)
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
            val bgColor = "#${team.color}"

            teamNameTextView.text = teamName
            recordTextView.text = "(${record})"

            Picasso.get().load(logo).into(logoImageView)
            view.setBackgroundColor(Color.parseColor(bgColor));


        }

    }
}