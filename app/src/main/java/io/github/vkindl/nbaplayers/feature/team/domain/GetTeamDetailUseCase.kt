package io.github.vkindl.nbaplayers.feature.team.domain

import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.domain.model.Team
import kotlinx.coroutines.flow.Flow

class GetTeamDetailUseCase(
    private val teamRepository: TeamRepository
) {
    operator fun invoke(id: Int): Flow<Result<Team>> {
        return teamRepository.getTeamDetail(id)
    }
}
