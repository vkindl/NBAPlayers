package io.github.vkindl.nbaplayers.feature.playerdetail.domain

import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.domain.model.Player
import io.github.vkindl.nbaplayers.core.domain.model.Team
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetPlayerUseCaseTest {

    private val repository: PlayerDetailRepository = mockk()
    private lateinit var sut: GetPlayerUseCase

    @Before
    fun setUp() {
        sut = GetPlayerUseCase(repository = repository)
    }

    @Test
    fun `should return player details from repository`() = runTest {
        coEvery { repository.getPlayerById(ANY_ID) } returns flowOf(Result.Success(expectedPlayer))

        val result = sut(ANY_ID).first()

        assertEquals(Result.Success(expectedPlayer), result)
    }

    @Test
    fun `should return error from repository`() = runTest {
        coEvery { repository.getPlayerById(ANY_ID) } returns flowOf(Result.Error(ERROR_MESSAGE))

        val result = sut(ANY_ID).first()

        assertEquals(Result.Error(ERROR_MESSAGE), result)
    }

    private companion object {
        const val ANY_ID = 1
        const val ERROR_MESSAGE = "Error message"
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
