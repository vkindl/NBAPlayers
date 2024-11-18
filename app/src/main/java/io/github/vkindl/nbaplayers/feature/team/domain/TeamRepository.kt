package io.github.vkindl.nbaplayers.feature.team.domain

import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface TeamRepository {

    fun getTeamDetail(id: Int): Flow<Result<Team>>
}
