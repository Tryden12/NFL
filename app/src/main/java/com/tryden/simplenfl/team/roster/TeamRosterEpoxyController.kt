package com.tryden.simplenfl.team.roster

import android.graphics.Color
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelRosterHeaderBinding
import com.tryden.simplenfl.databinding.ModelRosterPlayerItemBinding
import com.tryden.simplenfl.epoxy.LoadingEpoxyModel
import com.tryden.simplenfl.network.response.teams.models.roster.Roster

class TeamRosterEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }
    var rosterResponse: Roster? = null
        set(value) {
            field = value
            if (value != null) {
                isLoading = false
                requestModelBuild()
            }
        }


    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (rosterResponse == null) {
            // todo error state
            return
        }

        val rosterType = rosterResponse!!.athletes
        var headshot = rosterResponse!!.athletes[0].items[0].fullName

        for (i in rosterType.indices) {
            // Add the roster header by roster type (Offense, Defense, Special Teams
            RosterTypeHeaderEpoxyHeader(rosterType = rosterType[i].position)
                .id(rosterType[i].position).addTo(this)

            // Add all players listed in the roster type
            for (j in rosterType[i].items.indices)
                if (j == 0 || j % 2 == 0) {
                    RosterPlayerItemEpoxyModel(
                        imageUrl = rosterResponse!!.athletes[0].items[j].headshot.href,
                        name = rosterResponse!!.athletes[0].items[j].displayName,
                        number = rosterResponse!!.athletes[0].items[j].jersey,
                        position = rosterResponse!!.athletes[0].items[j].position.abbreviation,
                        age = rosterResponse!!.athletes[0].items[j].age.toString(),
                        height = rosterResponse!!.athletes[0].items[j].displayHeight,
                        backgroundColor = "#2B2B2B" // dark background
                    ).id(rosterResponse!!.athletes[0].items[j].id).addTo(this)
                } else {
                    RosterPlayerItemEpoxyModel(
                        imageUrl = rosterResponse!!.athletes[0].items[j].headshot.href,
                        name = rosterResponse!!.athletes[0].items[j].displayName,
                        number = rosterResponse!!.athletes[0].items[j].jersey,
                        position = rosterResponse!!.athletes[0].items[j].position.abbreviation,
                        age = rosterResponse!!.athletes[0].items[j].age.toString(),
                        height = rosterResponse!!.athletes[0].items[j].displayHeight,
                        backgroundColor = "#323232" // lighter background
                    ).id(rosterResponse!!.athletes[0].items[j].id).addTo(this)
                }

        }
    }

    // Add roster type header
    data class RosterTypeHeaderEpoxyHeader(
        val rosterType: String
    ): ViewBindingKotlinModel<ModelRosterHeaderBinding>(R.layout.model_roster_header) {

        override fun ModelRosterHeaderBinding.bind() {
            seasonTypeTextview.text = rosterType
        }
    }

    data class RosterPlayerItemEpoxyModel(
        val imageUrl: String,
        val name: String,
        val number: String,
        val position: String,
        val age: String,
        val height: String,
        val backgroundColor: String
    ): ViewBindingKotlinModel<ModelRosterPlayerItemBinding>(R.layout.model_roster_player_item) {

        override fun ModelRosterPlayerItemBinding.bind() {

            parentConstraintLayout.setBackgroundColor(Color.parseColor(backgroundColor))

            Picasso.get().load(imageUrl).into(playerImageView)
            nameTextView.text = name
            numberTextView.text = number
            positionTextView.text = position
            ageTextView.text = age
            heightTextView.text = height

        }
    }
}