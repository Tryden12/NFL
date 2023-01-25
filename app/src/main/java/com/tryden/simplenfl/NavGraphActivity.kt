package com.tryden.simplenfl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavGraphActivity: AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_graph)
        supportActionBar?.hide()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    navHostFragment.navController.navigate(R.id.action_teamsListFragment_to_homeFragment)
                    true
                }
                R.id.teamsListFragment -> {
                    navHostFragment.navController.navigate(R.id.action_homeFragment_to_teamsListFragment)
                    true
                }
                R.id.scoresPage -> {
                    true
                }
                R.id.newsPage -> {
                    true
                }
                else -> false
            }
        }


    }

    private fun setupBottomNav() {

    }
}