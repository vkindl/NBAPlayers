package io.github.vkindl.nbaplayers.core.data.mapper

import io.github.vkindl.nbaplayers.core.data.model.team.TeamDto
import io.github.vkindl.nbaplayers.core.domain.model.Team

fun TeamDto.toDomain(): Team {
    return Team(
        id = id,
        abbreviation = abbreviation,
        name = fullName,
        city = city,
        conference = conference,
        division = division
    )
}
