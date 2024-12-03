package io.github.vkindl.nbaplayers.feature.players.domain

import androidx.paging.PagingData
import io.github.vkindl.nbaplayers.core.domain.model.Player
import io.github.vkindl.nbaplayers.core.domain.model.Team
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetPlayersUseCaseTest {

    private lateinit var playerRepository: PlayerRepository

    private lateinit var sut: GetPlayersUseCase

    @Before
    fun setUp() {
        playerRepository = mockk()

        sut = GetPlayersUseCase(
            playerRepository = playerRepository
        )
    }

    @Test
    fun `should return players paging data from repository on invoke`() = runTest{
        every { playerRepository.getPlayers() } returns flowOf(playersPagingData)

        val result = sut.invoke().first()

        verify { playerRepository.getPlayers() }
        assertEquals(playersPagingData, result)
    }

    private companion object {
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
        val playersPagingData = PagingData.from(listOf(expectedPlayer))
    }
}
