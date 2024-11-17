package io.github.vkindl.nbaplayers.core.domain.model

data class Player(
    val id: Int,
    val fullName: String,
    val position: String,
    val jerseyNumber: String,
    val height: String,
    val weight: String,
    val country: String,
    val draftYear: String,
    val team: Team
)