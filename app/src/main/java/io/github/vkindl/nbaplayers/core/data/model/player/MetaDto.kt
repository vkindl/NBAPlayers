package io.github.vkindl.nbaplayers.core.data.model.player

import com.squareup.moshi.Json

data class MetaDto(
    @Json(name = "next_cursor")
    val nextCursor: Int,
    @Json(name = "per_page")
    val perPage: Int
)
