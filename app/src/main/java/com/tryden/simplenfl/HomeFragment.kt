package com.tryden.simplenfl

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onResume() {
        super.onResume()

        view?.postDelayed({
            val navGraphActivity = activity as NavGraphActivity
            navGraphActivity.navController.navigate(R.id.action_homeFragment_to_teamsListFragment)
        }, 3_000)
    }
}