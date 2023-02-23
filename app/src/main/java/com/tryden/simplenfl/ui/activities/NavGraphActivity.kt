package com.tryden.simplenfl.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.application.SimpleNFLApplication
import com.tryden.simplenfl.databinding.ActivityNavGraphBinding

class NavGraphActivity: AppCompatActivity() {

    private lateinit var binding: ActivityNavGraphBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(SimpleNFLApplication.context, R.color.black)

        setupNavHost()
    }

    // Setup nav host and controller
    private fun setupNavHost() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigationView.apply {
            navController.let {
                NavigationUI.setupWithNavController(this, it)
                setOnItemSelectedListener { item ->
                    NavigationUI.onNavDestinationSelected(item, it)
                    true
                }
                setOnItemReselectedListener { menuItem ->
                    navController.popBackStack(destinationId = menuItem.itemId, false)
                    NavigationUI.onNavDestinationSelected(menuItem, it)
                }
            }
        }
    }
}