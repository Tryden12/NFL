package com.tryden.simplenfl

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import com.tryden.simplenfl.constants.Constants.BASE_URL_NFL
import com.tryden.simplenfl.teams.models.team.TeamObject
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val teamNameTextView = findViewById<TextView>(R.id.teamNameTextView)
        val recordTextView = findViewById<TextView>(R.id.recordTextView)
        val logoImageView = findViewById<ImageView>(R.id.logoImageView)

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_NFL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val nflService: NFLService = retrofit.create(NFLService::class.java)

        nflService.getTeamById(10).enqueue(object : Callback<TeamObject> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<TeamObject>, response: Response<TeamObject>) {
                Log.e(TAG, response.toString() )
                if (!response.isSuccessful) {
                    Toast.makeText(
                        this@MainActivity,
                        "Unsuccessful network call",
                        Toast.LENGTH_SHORT
                    )
                }
                val body = response.body()!!
                val team = body.team
                val teamName = body.team.displayName
                val record = team.record.items[0].summary
                val logo = team.logos[1].href

                teamNameTextView.text = teamName
                recordTextView.text = "(${record})"

                Picasso.get().load(logo).into(logoImageView)
            }

            override fun onFailure(call: Call<TeamObject>, t: Throwable) {
                Log.e(TAG, t.message ?: "Null message" )
            }
        })
    }
}