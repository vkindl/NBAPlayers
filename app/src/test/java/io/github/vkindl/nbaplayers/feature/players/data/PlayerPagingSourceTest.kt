package io.github.vkindl.nbaplayers.feature.players.data

import androidx.paging.PagingSource
import io.github.vkindl.nbaplayers.core.data.api.NbaApi
import io.github.vkindl.nbaplayers.core.data.model.player.MetaDto
import io.github.vkindl.nbaplayers.core.data.model.player.PlayerDto
import io.github.vkindl.nbaplayers.core.data.model.player.PlayersResponseDto
import io.github.vkindl.nbaplayers.core.data.model.team.TeamDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PlayerPagingSourceTest {

    private lateinit var api: NbaApi

    private lateinit var sut: PlayerPagingSource

    @Before
    fun setUp() {
        api = mockk {
            coEvery { getPlayers(any(), any()) } returns playersResponse
        }
        sut = PlayerPagingSource(
            api = api
        )
    }

    @Test
    fun `should return success result when api call is successful`() = runTest {
        val params = PagingSource.LoadParams.Refresh(
            key = PAGE_NUMBER,
            loadSize = PAGE_SIZE,
            placeholdersEnabled = false
        )

        val result = sut.load(params)

        val expected = PagingSource.LoadResult.Page(
            data = players,
            prevKey = null,
            nextKey = 2
        )

        assertEquals(expected, result)
    }

    private companion object {
        const val PAGE_NUMBER = 1
        const val PAGE_SIZE = 35
        val players = listOf(
            PlayerDto(
                id = 1,
                college = "college",
                country = "country",
                draftNumber = 3,
                draftRound = 1,
                draftYear = 1999,
                firstName = "Joe",
                height = "6-6",
                jerseyNumber = "99",
                lastName = "Doe",
                position = "G",
                weight = "200",
                team = TeamDto(
                    id = 1,
                    abbreviation = "abbreviation",
                    city = "city",
                    conference = "conference",
                    division = "division",
                    name = "name",
                    fullName = "fullName"
                )
            )
        )
        val playersResponse = PlayersResponseDto(
            players = players,
            meta = MetaDto(
                nextCursor = 1,
                perPage = 35
            )
        )
    }
}
