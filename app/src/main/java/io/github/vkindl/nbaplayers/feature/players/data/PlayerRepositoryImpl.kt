package io.github.vkindl.nbaplayers.feature.players.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import io.github.vkindl.nbaplayers.core.data.mapper.toDomain
import io.github.vkindl.nbaplayers.core.data.model.player.PlayerDto
import io.github.vkindl.nbaplayers.core.domain.model.Player
import io.github.vkindl.nbaplayers.feature.players.domain.PlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlayerRepositoryImpl(
    private val pagingSource: PagingSource<Int, PlayerDto>
) : PlayerRepository {

    override fun getPlayers(): Flow<PagingData<Player>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ),
            pagingSourceFactory = { pagingSource }
        ).flow.map { pagingData -> pagingData.map { it.toDomain() } }
    }

    private companion object {
        const val PAGE_SIZE = 35
    }
}
