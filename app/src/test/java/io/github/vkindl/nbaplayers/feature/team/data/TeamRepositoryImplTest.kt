package io.github.vkindl.nbaplayers.feature.team.data

import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.data.api.NbaApi
import io.github.vkindl.nbaplayers.core.data.model.team.TeamDto
import io.github.vkindl.nbaplayers.core.data.model.team.TeamResponseDto
import io.github.vkindl.nbaplayers.core.domain.model.Team
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TeamRepositoryImplTest {

    private lateinit var nbaApi: NbaApi
    private lateinit var sut: TeamRepositoryImpl

    @Before
    fun setUp() {
        nbaApi = mockk()

        sut = TeamRepositoryImpl(nbaApi = nbaApi)
    }

    @Test
    fun `should return success result when api call is successful`() = runTest {
        coEvery { nbaApi.getTeamById(1) } returns TeamResponseDto(team = teamDto)

        val result = sut.getTeamDetail(1).first()

       assertEquals(Result.Success(expectedTeam), result)
    }

    @Test
    fun `should return error result when api call fails`() = runTest {
        coEvery { nbaApi.getTeamById(1) } throws exception

        val result = sut.getTeamDetail(1).first()

        assertEquals(Result.Error(exception.localizedMessage), result)
    }

    private companion object {
        val teamDto = TeamDto(
            id = 1,
            fullName = "Test Team",
            name = "Test",
            abbreviation = "TT",
            city = "Test City",
            conference = "East",
            division = "Atlantic"
        )
        val expectedTeam = Team(
            id = 1,
            name = "Test Team",
            abbreviation = "TT",
            city = "Test City",
            conference = "East",
            division = "Atlantic"
        )
        const val ERROR_MESSAGE = "Error message"
        val exception = Exception(ERROR_MESSAGE)
    }
}
