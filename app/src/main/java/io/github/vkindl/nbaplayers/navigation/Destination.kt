package io.github.vkindl.nbaplayers.navigation

import kotlinx.serialization.Serializable

sealed class Destination {

    @Serializable
    data object Players : Destination()

    @Serializable
    data object PlayerDetail : Destination()

    @Serializable
    data object Team: Destination()
}
