package com.tryden.simplenfl

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.teams.TeamListHomeEpoxyController


class TeamsListFragment : Fragment() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyControllerTeamList = TeamListHomeEpoxyController(::onTeamSelected) // function pointer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyTeamListRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxy_team_list_RecyclerView)


        viewModel.allTeamsListLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerTeamList.teamsListResponse = response
        }

        viewModel.refreshTeamsList()

        epoxyTeamListRecyclerView.setControllerAndBuildModels(epoxyControllerTeamList)
    }

    private fun onTeamSelected(teamId: Int) {
        Log.e(MainActivityTeamsList.TAG, "onTeamSelected: $teamId")
//        val intent = Intent(this, MainActivityRoster::class.java)
//        intent.putExtra("test", teamId)
//        startActivity(intent)

        // todo findNavController().navigate(R.id.action)
    }

}