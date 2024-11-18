package io.github.vkindl.nbaplayers.feature.team.data

import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.data.api.NbaApi
import io.github.vkindl.nbaplayers.core.data.mapper.toDomain
import io.github.vkindl.nbaplayers.core.domain.model.Team
import io.github.vkindl.nbaplayers.feature.team.domain.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TeamRepositoryImpl(
    private val api: NbaApi
) : TeamRepository {

    override fun getTeamDetail(id: Int): Flow<Result<Team>> {
        return flow {
            runCatching { api.getTeamById(id) }.fold(
                onSuccess = { emit(Result.Success(it.team.toDomain())) },
                onFailure = { emit(Result.Error(it.localizedMessage)) }
            )
        }
    }
}
