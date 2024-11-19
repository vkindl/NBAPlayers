package io.github.vkindl.nbaplayers.feature.team.domain

import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.domain.model.Team
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetTeamDetailUseCaseTest {

    private val repository: TeamRepository = mockk()
    private lateinit var sut: GetTeamDetailUseCase

    @Before
    fun setUp() {
        sut = GetTeamDetailUseCase(repository = repository)
    }

    @Test
    fun `should return team details from repository`() = runTest {
        coEvery { repository.getTeamDetail(ANY_ID) } returns flowOf(Result.Success(expectedTeam))

        val result = sut(ANY_ID).first()

        assertEquals(Result.Success(expectedTeam), result)
    }

    @Test
    fun `should return error from repository`() = runTest {
        coEvery { repository.getTeamDetail(ANY_ID) } returns flowOf(Result.Error(ERROR_MESSAGE))

        val result = sut(ANY_ID).first()

        assertEquals(Result.Error(ERROR_MESSAGE), result)
    }

    private companion object {
        const val ANY_ID = 1
        const val ERROR_MESSAGE = "Error message"
        val expectedTeam = Team(
            id = 1,
            name = "Golden State Warriors",
            abbreviation = "GSW",
            city = "San Francisco",
            conference = "Western",
            division = "Pacific"
        )
    }
}
