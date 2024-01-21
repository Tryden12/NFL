package com.tryden.simplenfl.domain.newmapper

import com.tryden.simplenfl.data.remote.dto.RosterDto
import com.tryden.simplenfl.domain.models.roster.Player
import javax.inject.Inject

class RosterMapper @Inject constructor() : Mapper<Player, RosterDto.Item> {

    override fun buildFrom(value: RosterDto.Item) : Player {
        return Player(
            id = value.id,
            displayName = value.displayName,
            shortName = value.shortName,
            firstName = value.firstName,
            lastName = value.lastName,
            displayHeight = value.displayHeight,
            displayWeight = value.displayWeight,
            age = value.age.toString(),
            experience = value.experience.years,
            headshot = value.headshot.href,
            jersey = value.jersey,
            position = value.position.abbreviation,
            group = value.position.parent.displayName
        )
    }
}