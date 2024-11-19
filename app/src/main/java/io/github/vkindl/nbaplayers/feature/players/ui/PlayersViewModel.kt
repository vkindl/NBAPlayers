package io.github.vkindl.nbaplayers.feature.players.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import io.github.vkindl.nbaplayers.feature.players.domain.GetPlayersUseCase

class PlayersViewModel(
    getPlayersUseCase: GetPlayersUseCase,
) : ViewModel() {

    val playersPagingData = getPlayersUseCase()
        .cachedIn(viewModelScope)
}
