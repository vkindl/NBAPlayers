package io.github.vkindl.nbaplayers.feature.playerdetail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.domain.model.Player
import io.github.vkindl.nbaplayers.core.navigation.Destination
import io.github.vkindl.nbaplayers.feature.playerdetail.domain.GetPlayerUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PlayerDetailViewModel(
    getPlayerUseCase: GetPlayerUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val playerId = savedStateHandle.toRoute<Destination.PlayerDetail>().id

    val state: StateFlow<PlayerDetailUiState> =
        getPlayerUseCase(id = playerId).map { it.toUiState() }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                PlayerDetailUiState.Loading
            )

    private fun Result<Player>.toUiState(): PlayerDetailUiState {
        return when (this) {
            is Result.Loading -> PlayerDetailUiState.Loading
            is Result.Success -> PlayerDetailUiState.Success(data)
            is Result.Error -> PlayerDetailUiState.Error(errorMessage.orEmpty())
        }
    }

    sealed interface PlayerDetailUiState {
        data object Loading : PlayerDetailUiState
        data class Success(val player: Player) : PlayerDetailUiState
        data class Error(val errorMessage: String) : PlayerDetailUiState
    }
}
