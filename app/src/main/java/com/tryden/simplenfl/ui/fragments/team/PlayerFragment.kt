package com.tryden.simplenfl.ui.fragments.team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.epoxy.controllers.team.player.PlayerEpoxyController

class PlayerFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()


    private val epoxyControllerPlayer = PlayerEpoxyController()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyPlayerRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxy_player_RecyclerView)

        sharedViewModel.playerByIdLiveData.observe(viewLifecycleOwner) { response ->
            epoxyControllerPlayer.playerResponse = response
        }

        sharedViewModel.refreshPlayer("14876")
        epoxyPlayerRecyclerView.setControllerAndBuildModels(epoxyControllerPlayer)

    }
}