package io.github.vkindl.nbaplayers.feature.players.data

import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.data.api.NbaApi
import io.github.vkindl.nbaplayers.core.data.mapper.toDomain
import io.github.vkindl.nbaplayers.core.domain.model.Player
import io.github.vkindl.nbaplayers.feature.players.domain.PlayerRepository

class PlayerRepositoryImpl(
    private val api: NbaApi
) : PlayerRepository {

    override suspend fun getPlayers(page: Int, perPage: Int): Result<List<Player>> {
        return runCatching { api.getPlayers(page, perPage) }.fold(
            onSuccess = { Result.Success(it.players.map { it.toDomain() }) },
            onFailure = { Result.Error(it.localizedMessage) }
        )
    }
}
