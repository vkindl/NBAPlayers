package io.github.vkindl.nbaplayers.feature.playerdetail.data

import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.data.api.NbaApi
import io.github.vkindl.nbaplayers.core.data.mapper.toDomain
import io.github.vkindl.nbaplayers.core.domain.model.Player
import io.github.vkindl.nbaplayers.feature.playerdetail.domain.PlayerDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayerDetailRepositoryImpl(
    private val api: NbaApi
) : PlayerDetailRepository {
    override fun getPlayerDetail(id: Int): Flow<Result<Player>> {
        return flow {
            runCatching { api.getPlayerById(id) }.fold(
                onSuccess = { emit(Result.Success(it.player.toDomain())) },
                onFailure = { emit(Result.Error(it.localizedMessage)) }
            )
        }
    }
}
