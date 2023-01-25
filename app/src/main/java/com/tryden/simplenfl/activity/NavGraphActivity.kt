package com.tryden.simplenfl.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tryden.simplenfl.R

class NavGraphActivity: AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_graph)
        supportActionBar?.hide()

        setupNavHost()
        bottomNavActions()
    }

    // Setup nav host and controller
    private fun setupNavHost() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
    }

    // Handles bottom nav actions based off current fragment
    private fun bottomNavActions() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            val currentFragmentId = navController.currentDestination?.id

            when (item.itemId) { // bottom nav button

                R.id.homeFragment -> {
                    when (currentFragmentId) { // decide which nav action
                        R.id.homeFragment -> true
                        R.id.scoresFragment -> navHostFragment.navController.navigate(R.id.action_scoresFragment_to_homeFragment)
                        R.id.teamsListFragment -> navHostFragment.navController.navigate(R.id.action_teamsListFragment_to_homeFragment)
                        R.id.newsFragment -> navHostFragment.navController.navigate(R.id.action_newsFragment_to_homeFragment)
                    }
                    true
                }
                R.id.scoresFragment -> {
                    when (currentFragmentId) {
                        R.id.scoresFragment -> true
                        R.id.homeFragment -> navHostFragment.navController.navigate(R.id.action_homeFragment_to_scoresFragment)
                        R.id.teamsListFragment -> navHostFragment.navController.navigate(R.id.action_teamsListFragment_to_scoresFragment)
                        R.id.newsFragment -> navHostFragment.navController.navigate(R.id.action_newsFragment_to_scoresFragment)
                    }
                    true
                }
                R.id.teamsListFragment -> {
                    when (currentFragmentId) {
                        R.id.teamsListFragment -> true
                        R.id.homeFragment -> navHostFragment.navController.navigate(R.id.action_homeFragment_to_teamsListFragment)
                        R.id.scoresFragment -> navHostFragment.navController.navigate(R.id.action_scoresFragment_to_teamsListFragment)
                        R.id.newsFragment -> navHostFragment.navController.navigate(R.id.action_newsFragment_to_teamsListFragment)
                    }
                    true
                }
                R.id.newsFragment -> {
                    when (currentFragmentId) {
                        R.id.newsFragment -> true
                        R.id.homeFragment -> navHostFragment.navController.navigate(R.id.action_homeFragment_to_newsFragment)
                        R.id.scoresFragment -> navHostFragment.navController.navigate(R.id.action_scoresFragment_to_newsFragment)
                        R.id.teamsListFragment -> navHostFragment.navController.navigate(R.id.action_teamsListFragment_to_newsFragment)
                    }
                    true
                }
                else -> false
            }
        }
    }
}