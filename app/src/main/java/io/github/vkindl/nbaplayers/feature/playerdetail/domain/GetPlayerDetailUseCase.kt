package io.github.vkindl.nbaplayers.feature.playerdetail.domain

import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.domain.model.Player
import kotlinx.coroutines.flow.Flow

class GetPlayerDetailUseCase(
    private val repository: PlayerDetailRepository
) {
    operator fun invoke(id: Int): Flow<Result<Player>> {
        return repository.getPlayerDetail(id)
    }
}
