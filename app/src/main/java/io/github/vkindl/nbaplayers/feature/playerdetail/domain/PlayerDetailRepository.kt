package io.github.vkindl.nbaplayers.feature.playerdetail.domain

import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.domain.model.Player
import kotlinx.coroutines.flow.Flow

interface PlayerDetailRepository {

    fun getPlayerDetail(id: Int): Flow<Result<Player>>
}
