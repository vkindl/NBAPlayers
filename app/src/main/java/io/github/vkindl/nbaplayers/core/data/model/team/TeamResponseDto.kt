package io.github.vkindl.nbaplayers.core.data.model.team

import com.squareup.moshi.Json

data class TeamResponseDto(
    @Json(name = "data")
    val team: TeamDto
)
