package com.tryden.simplenfl.epoxy.controllers.team.player

import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.simplenfl.R
import com.tryden.simplenfl.databinding.ModelPlayerHeaderBinding
import com.tryden.simplenfl.epoxy.controllers.LoadingEpoxyModel
import com.tryden.simplenfl.network.response.teams.models.player.PlayerResponse

class PlayerEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }
    var playerResponse: PlayerResponse? = null
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

        if (playerResponse == null) {
            // todo error state
            return
        }

        PlayerHeaderEpoxyModel(
            playerImageUrl = playerResponse!!.headshot.href,
            firstName = playerResponse!!.firstName,
            lastName = playerResponse!!.lastName,
            number = playerResponse!!.jersey,
            position = playerResponse!!.position.abbreviation,
            height = playerResponse!!.displayHeight,
            weight = playerResponse!!.displayWeight,
            college = "LSU" // todo: this field is an href string to a new page
        ).id(playerResponse!!.id).addTo(this)

    }

    // Player header model
    data class PlayerHeaderEpoxyModel(
        val playerImageUrl: String,
        val firstName: String,
        val lastName: String,
        val number: String,
        val position: String,
        val height: String,
        val weight: String,
        val college: String
    ): ViewBindingKotlinModel<ModelPlayerHeaderBinding>(R.layout.model_player_header) {

        override fun ModelPlayerHeaderBinding.bind() {

            if (playerImageUrl.isEmpty()) {
                Picasso.get()
                    .load(R.drawable.placeholder_headshot)
                    .placeholder(R.drawable.placeholder_headshot)
                    .error(R.drawable.placeholder_headshot)
                    .into(playerImageImageView)
            } else {
                Picasso.get().load(playerImageUrl).into(playerImageImageView)
            }

            firstNameTextView.text = firstName
            lastNameTextView.text = lastName
            numberTextView.text = "#$number"
            positionTextView.text = position
            heightWeightTextView.text = "$height, $weight"
            collegeTextView.text = college
        }
    }
}