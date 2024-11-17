package io.github.vkindl.nbaplayers.core.data.mapper

import io.github.vkindl.nbaplayers.core.data.model.player.PlayerDto
import io.github.vkindl.nbaplayers.core.domain.model.Player

fun PlayerDto.toDomain(): Player {
    return Player(
        id = id,
        fullName = "$firstName $lastName",
        position = position,
        jerseyNumber = jerseyNumber,
        height = height,
        weight = weight,
        country = country,
        draftYear = draftYear?.toString().orEmpty(),
        team = team.toDomain()
    )
}
