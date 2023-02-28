package com.tryden.simplenfl.domain.mappers.team

import com.tryden.simplenfl.domain.models.team.Logo

object TeamLogoMapper {

    fun buildFrom(logoUrl: String): Logo {
        return Logo( logoUrl = logoUrl)
    }
}