package io.github.vkindl.nbaplayers.feature.players.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.vkindl.nbaplayers.core.data.api.NbaApi
import io.github.vkindl.nbaplayers.core.data.model.player.PlayerDto

class PlayerPagingSource(
    private val api: NbaApi
) : PagingSource<Int, PlayerDto>() {

    override fun getRefreshKey(state: PagingState<Int, PlayerDto>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlayerDto> {
        return try {
            val page = params.key ?: INITIAL_PAGE
            val response = api.getPlayers(page, ITEMS_PER_PAGE)
            LoadResult.Page(
                data = response.players,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.players.isEmpty()) null else page + 1
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
