package com.tryden.simplenfl.domain.mappers.team

import com.tryden.simplenfl.domain.models.roster.Player
import com.tryden.simplenfl.data.remote.dto.RosterDto

object TeamRosterMapper {
    fun buildFrom(player: RosterDto.Item) : Player {
        return Player(
            id = player.id,
            displayName = player.displayName,
            shortName = player.shortName,
            firstName = player.firstName,
            lastName = player.lastName,
            displayHeight = player.displayHeight,
            displayWeight = player.displayWeight,
            age = player.age.toString(),
            experience = player.experience.years,
            headshot = player.headshot.href,
            jersey = player.jersey,
            position = player.position.abbreviation,
            group = player.position.parent.displayName
        )
    }
}