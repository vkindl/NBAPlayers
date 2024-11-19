package io.github.vkindl.nbaplayers.feature.playerdetail.data

import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.data.api.NbaApi
import io.github.vkindl.nbaplayers.core.data.model.player.PlayerDto
import io.github.vkindl.nbaplayers.core.data.model.player.PlayerResponseDto
import io.github.vkindl.nbaplayers.core.data.model.team.TeamDto
import io.github.vkindl.nbaplayers.core.domain.model.Player
import io.github.vkindl.nbaplayers.core.domain.model.Team
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PlayerDetailRepositoryImplTest {

    private val api: NbaApi = mockk()
    private lateinit var sut: PlayerDetailRepositoryImpl

    @Before
    fun setUp() {
        sut = PlayerDetailRepositoryImpl(api = api)
    }

    @Test
    fun `should return player details`() = runTest {
        coEvery { api.getPlayerById(1) } returns playerResponse

        val result = sut.getPlayerDetail(1).first()

        assertEquals(Result.Success(expectedPlayer), result)
    }

    @Test
    fun `should return error when api call fails`() = runTest {
        coEvery { api.getPlayerById(1) } throws Exception("Error message")

        val result = sut.getPlayerDetail(1).first()

        assertEquals(Result.Error("Error message"), result)
    }

    private companion object {
        val playerResponse = PlayerResponseDto(
            player = PlayerDto(
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
        )
        val expectedPlayer = Player(
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
    }
}
