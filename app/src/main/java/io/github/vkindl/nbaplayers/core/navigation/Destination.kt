package io.github.vkindl.nbaplayers.core.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {

    @Serializable
    data object Players : Destination

    @Serializable
    data class PlayerDetail(val id: Int) : Destination

    @Serializable
    data class Team(val id: Int): Destination
}
