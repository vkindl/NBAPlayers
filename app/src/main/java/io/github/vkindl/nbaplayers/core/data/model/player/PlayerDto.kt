package io.github.vkindl.nbaplayers.core.data.model.player

import com.squareup.moshi.Json
import io.github.vkindl.nbaplayers.core.data.model.team.TeamDto

data class PlayerDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "college")
    val college: String,
    @Json(name = "country")
    val country: String,
    @Json(name = "draft_number")
    val draftNumber: Int?,
    @Json(name = "draft_round")
    val draftRound: Int?,
    @Json(name = "draft_year")
    val draftYear: Int?,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "height")
    val height: String,
    @Json(name = "jersey_number")
    val jerseyNumber: String,
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "position")
    val position: String,
    @Json(name = "team")
    val team: TeamDto,
    @Json(name = "weight")
    val weight: String
)
