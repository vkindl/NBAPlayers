package io.github.vkindl.nbaplayers.core.domain.model

data class Team(
    val id: Int,
    val abbreviation: String,
    val name: String,
    val city: String,
    val conference: String,
    val division: String
)
