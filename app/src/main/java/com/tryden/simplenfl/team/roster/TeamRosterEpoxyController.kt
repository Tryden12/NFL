package com.tryden.simplenfl.team.roster

import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SimpleNFLApplication
import com.tryden.simplenfl.databinding.ModelRosterHeaderBinding
import com.tryden.simplenfl.databinding.ModelRosterPlayerItemBinding
import com.tryden.simplenfl.epoxy.LoadingEpoxyModel
import com.tryden.simplenfl.network.response.teams.models.roster.RosterResponse

class TeamRosterEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }
    var rosterResponse: RosterResponse? = null
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

        for (i in rosterResponse!!.athletes.indices) {
            // Add the roster header by roster type (Offense, Defense, Special Teams
            RosterTypeHeaderEpoxyHeader(rosterType = rosterType[i].position)
                .id(rosterType[i].position).addTo(this)

            // Add all players listed in the roster type
            for (j in rosterResponse!!.athletes[i].items.indices)
                if (j % 2 == 0) {
                    RosterPlayerItemEpoxyModel(
                        imageUrl = rosterResponse!!.athletes[i].items[j].headshot.href,
                        name = rosterResponse!!.athletes[i].items[j].displayName,
                        number = rosterResponse!!.athletes[i].items[j].jersey,
                        position = rosterResponse!!.athletes[i].items[j].position.abbreviation,
                        age = rosterResponse!!.athletes[i].items[j].age.toString(),
                        height = rosterResponse!!.athletes[i].items[j].displayHeight,
                        backgroundColor = ContextCompat.getColor(SimpleNFLApplication.context, R.color.dark_grey) // dark background
                    ).id(rosterResponse!!.athletes[i].items[j].id).addTo(this)
                } else {
                    RosterPlayerItemEpoxyModel(
                        imageUrl = rosterResponse!!.athletes[i].items[j].headshot.href,
                        name = rosterResponse!!.athletes[i].items[j].displayName,
                        number = rosterResponse!!.athletes[i].items[j].jersey,
                        position = rosterResponse!!.athletes[i].items[j].position.abbreviation,
                        age = rosterResponse!!.athletes[i].items[j].age.toString(),
                        height = rosterResponse!!.athletes[i].items[j].displayHeight,
                        backgroundColor = ContextCompat.getColor(SimpleNFLApplication.context, R.color.darkish_grey) // lighter background
                    ).id(rosterResponse!!.athletes[i].items[j].id).addTo(this)
                }

        }
    }

    // Roster type header
    data class RosterTypeHeaderEpoxyHeader(
        val rosterType: String
    ): ViewBindingKotlinModel<ModelRosterHeaderBinding>(R.layout.model_roster_header) {

        override fun ModelRosterHeaderBinding.bind() {
            seasonTypeTextview.text = rosterType
        }
    }

    // Player item model
    data class RosterPlayerItemEpoxyModel(
        val imageUrl: String,
        val name: String,
        val number: String,
        val position: String,
        val age: String,
        val height: String,
        val backgroundColor: Int
    ): ViewBindingKotlinModel<ModelRosterPlayerItemBinding>(R.layout.model_roster_player_item) {

        override fun ModelRosterPlayerItemBinding.bind() {

            parentConstraintLayout.setBackgroundColor(backgroundColor)

            if (imageUrl.isEmpty()) {
                Picasso.get()
                    .load(R.drawable.placeholder_headshot)
                    .placeholder(R.drawable.placeholder_headshot)
                    .error(R.drawable.placeholder_headshot)
                    .into(playerImageImageView)
            } else {
                Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder_headshot)
                    .error(R.drawable.placeholder_headshot)
                    .into(playerImageImageView)
            }


            nameTextView.text = name
            numberTextView.text = number
            positionTextView.text = position
            ageTextView.text = age
            heightTextView.text = height

        }
    }
}