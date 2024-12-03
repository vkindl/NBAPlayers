package io.github.vkindl.nbaplayers.feature.team.ui

import io.github.vkindl.nbaplayers.MainDispatcherRule
import io.github.vkindl.nbaplayers.SavedStateHandleRule
import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.domain.model.Team
import io.github.vkindl.nbaplayers.core.navigation.Destination
import io.github.vkindl.nbaplayers.feature.team.domain.GetTeamDetailUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TeamViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    @get:Rule
    val savedStateHandleRule = SavedStateHandleRule(Destination.Team(id = TEAM_ID))

    private lateinit var getTeamDetailUseCase: GetTeamDetailUseCase

    private lateinit var sut: TeamViewModel

    @Before
    fun setUp() {
        getTeamDetailUseCase = mockk {
            every { this@mockk.invoke(any()) } returns flowOf(Result.Success(team))
        }

        sut = TeamViewModel(
            getTeamDetailUseCase = getTeamDetailUseCase,
            savedStateHandle = savedStateHandleRule.savedStateHandle
        )
    }

    @Test
    fun `should set loading state on init`() {
        assertEquals(TeamViewModel.TeamUiState.Loading, sut.state.value)
    }

    @Test
    fun `should set team details on success`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher()) {
            sut.state.collect()
        }

        val result = sut.state.value

        assertEquals(TeamViewModel.TeamUiState.Success(team), result)
    }

    private companion object {
        const val TEAM_ID = 1
        val team = Team(
            id = 1,
            name = "Golden State Warriors",
            abbreviation = "GSW",
            city = "San Francisco",
            conference = "Western",
            division = "Pacific"
        )
    }
}
