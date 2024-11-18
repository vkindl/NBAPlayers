package io.github.vkindl.nbaplayers.feature.players.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import io.github.vkindl.nbaplayers.core.domain.model.Player

class PlayersViewModel(
    pagingSource: PagingSource<Int, Player>
) : ViewModel() {

    val players = Pager(
        config = PagingConfig(pageSize = 35),
        pagingSourceFactory = { pagingSource }
    ).flow.cachedIn(viewModelScope)
}
