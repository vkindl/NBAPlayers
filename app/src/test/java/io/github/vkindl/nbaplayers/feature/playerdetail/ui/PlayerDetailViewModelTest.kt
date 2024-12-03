package io.github.vkindl.nbaplayers.feature.playerdetail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import io.github.vkindl.nbaplayers.MainDispatcherRule
import io.github.vkindl.nbaplayers.SavedStateHandleRule
import io.github.vkindl.nbaplayers.core.Result
import io.github.vkindl.nbaplayers.core.domain.model.Player
import io.github.vkindl.nbaplayers.core.domain.model.Team
import io.github.vkindl.nbaplayers.core.navigation.Destination
import io.github.vkindl.nbaplayers.feature.playerdetail.domain.GetPlayerDetailUseCase
import io.github.vkindl.nbaplayers.feature.playerdetail.ui.PlayerDetailViewModel.PlayerDetailUiState
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
class PlayerDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    @get:Rule
    val savedStateHandleRule = SavedStateHandleRule(Destination.PlayerDetail(id = 1))

    private lateinit var getPlayerDetailUseCase: GetPlayerDetailUseCase

    private lateinit var sut: PlayerDetailViewModel

    @Before
    fun setUp() {
        getPlayerDetailUseCase = mockk(relaxed = true) {
            every { this@mockk.invoke(any()) } returns flowOf(Result.Success(expectedPlayer))
        }

        sut = PlayerDetailViewModel(
            getPlayerDetailUseCase = getPlayerDetailUseCase,
            savedStateHandle = savedStateHandleRule.savedStateHandle
        )
    }

    @Test
    fun `should set loading state on init`() {
        assertEquals(PlayerDetailUiState.Loading, sut.state.value)
    }

    @Test
    fun `should set player detail`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher()) {
            sut.state.collect()
        }

        val result = sut.state.value


        assertEquals(PlayerDetailUiState.Success(expectedPlayer), result)
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
    }
}