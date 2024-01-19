package com.tryden.simplenfl.domain.newmapper

import com.tryden.simplenfl.data.remote.dto.AllTeamsDto
import com.tryden.simplenfl.domain.newmodels.TeamList

class TeamsListMapper : Mapper<TeamList, AllTeamsDto.Team> {

    override fun buildFrom(value: AllTeamsDto.Team) : TeamList {
        return TeamList(
            imageUrl = value.logos[2].href,
            name = value.displayName,
            id = value.id
        )
    }
}