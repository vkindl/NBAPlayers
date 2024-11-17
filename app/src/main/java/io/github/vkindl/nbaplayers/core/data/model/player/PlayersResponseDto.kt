package io.github.vkindl.nbaplayers.core.data.model.player

import com.squareup.moshi.Json

data class PlayersResponseDto(
    @Json(name = "data")
    val players: List<PlayerDto>,
    @Json(name = "meta")
    val meta: MetaDto
)
