package io.github.vkindl.nbaplayers.feature.players.domain

import androidx.paging.PagingData
import io.github.vkindl.nbaplayers.core.domain.model.Player
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {

    fun getPlayers(): Flow<PagingData<Player>>
}
