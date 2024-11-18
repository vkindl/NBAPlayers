package io.github.vkindl.nbaplayers.feature.players.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.domain.model.Player
import io.github.vkindl.nbaplayers.feature.players.domain.PlayerRepository

class PlayerPagingSource(
    private val repository: PlayerRepository
) : PagingSource<Int, Player>() {

    override fun getRefreshKey(state: PagingState<Int, Player>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Player> {
        return try {
            val page = params.key ?: INITIAL_PAGE
            val response = repository.getPlayers(page, ITEMS_PER_PAGE) as Result.Success
            LoadResult.Page(
                data = response.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private companion object {
        const val INITIAL_PAGE = 1
        const val ITEMS_PER_PAGE = 35
    }
}
