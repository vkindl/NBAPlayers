package io.github.vkindl.nbaplayers.feature.players.domain

import androidx.paging.PagingData
import io.github.vkindl.nbaplayers.core.domain.model.Player
import kotlinx.coroutines.flow.Flow

class GetPlayersUseCase(
    private val playerRepository: PlayerRepository
) {
    operator fun invoke(): Flow<PagingData<Player>> {
        return playerRepository.getPlayers()
    }
}
