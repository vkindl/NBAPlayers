package io.github.vkindl.nbaplayers.feature.players.data

import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.data.api.NbaApi
import io.github.vkindl.nbaplayers.core.data.model.player.MetaDto
import io.github.vkindl.nbaplayers.core.data.model.player.PlayerDto
import io.github.vkindl.nbaplayers.core.data.model.player.PlayersResponseDto
import io.github.vkindl.nbaplayers.core.data.model.team.TeamDto
import io.github.vkindl.nbaplayers.core.domain.model.Player
import io.github.vkindl.nbaplayers.core.domain.model.Team
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PlayerRepositoryImplTest {

    private val api: NbaApi = mockk()
    private lateinit var sut: PlayerRepositoryImpl

    @Before
    fun setUp() {
        sut = PlayerRepositoryImpl(api = api)
    }

    @Test
    fun `should return success result when api call is successful`() = runTest {
        coEvery { api.getPlayers(1, 10) } returns response

        val result = sut.getPlayers(1, 10)

        assertEquals(Result.Success(players), result)
    }

    @Test
    fun `should return error result when api call fails`() = runTest {
        val exception = Exception("Network error")
        coEvery { api.getPlayers(1, 10) } throws exception

        val result = sut.getPlayers(1, 10)

        assertEquals(Result.Error(exception.localizedMessage), result)
    }

    private companion object {
        val response = PlayersResponseDto(
            players = listOf(
                PlayerDto(
                    id = 1,
                    firstName = "LeBron",
                    lastName = "James",
                    height = "6-9",
                    weight = "250",
                    position = "G",
                    jerseyNumber = "23",
                    country = "USA",
                    draftYear = 2003,
                    draftRound = 1,
                    draftNumber = 1,
                    college = "",
                    team = TeamDto(
                        id = 1,
                        abbreviation = "LAL",
                        city = "Los Angeles",
                        conference = "West",
                        division = "Pacific",
                        fullName = "Los Angeles Lakers",
                        name = "Los Angeles Lakers"
                    )
                )
            ),
            meta = MetaDto(
                nextCursor = 1,
                perPage = 1
            )
        )

        val players = listOf(
            Player(
                id = 1,
                fullName = "LeBron James",
                position = "G",
                jerseyNumber = "23",
                height = "6-9",
                weight = "250",
                country = "USA",
                draftYear = "2003",
                team = Team(
                    id = 1,
                    abbreviation = "LAL",
                    city = "Los Angeles",
                    conference = "West",
                    division = "Pacific",
                    name = "Los Angeles Lakers"
                )
            )
        )
    }
}
