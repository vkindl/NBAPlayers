package io.github.vkindl.nbaplayers.feature.team.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.navigation.Destination
import io.github.vkindl.nbaplayers.core.domain.model.Team
import io.github.vkindl.nbaplayers.feature.team.domain.GetTeamDetailUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TeamViewModel(
    getTeamDetailUseCase: GetTeamDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val teamId = savedStateHandle.toRoute<Destination.Team>().id

    val state: StateFlow<TeamUiState> =
        getTeamDetailUseCase(id = teamId).map { it.toUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                TeamUiState.Loading
            )

    private fun Result<Team>.toUiState(): TeamUiState {
        return when (this) {
            is Result.Loading -> TeamUiState.Loading
            is Result.Success -> TeamUiState.Success(data)
            is Result.Error -> TeamUiState.Error(errorMessage.orEmpty())
        }
    }

    sealed interface TeamUiState {
        data object Loading : TeamUiState
        data class Success(val team: Team) : TeamUiState
        data class Error(val errorMessage: String) : TeamUiState
    }
}
