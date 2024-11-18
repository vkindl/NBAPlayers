package io.github.vkindl.nbaplayers.feature.players.domain

import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.domain.model.Player

interface PlayerRepository {

    suspend fun getPlayers(page: Int, perPage: Int): Result<List<Player>>
}
