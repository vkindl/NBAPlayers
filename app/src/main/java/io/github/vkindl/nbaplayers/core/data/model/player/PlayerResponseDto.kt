package io.github.vkindl.nbaplayers.core.data.model.player

import com.squareup.moshi.Json

data class PlayerResponseDto(
    @Json(name = "data")
    val player: PlayerDto
)
