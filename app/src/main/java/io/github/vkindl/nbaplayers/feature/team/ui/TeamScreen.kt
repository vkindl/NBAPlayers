package io.github.vkindl.nbaplayers.feature.team.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.vkindl.nbaplayers.R
import io.github.vkindl.nbaplayers.core.designsystem.component.BackIconButton
import io.github.vkindl.nbaplayers.core.designsystem.component.ErrorContent
import io.github.vkindl.nbaplayers.core.designsystem.component.FullScreenLoadingIndicator
import io.github.vkindl.nbaplayers.core.designsystem.component.KeyValueRow
import io.github.vkindl.nbaplayers.core.designsystem.component.NbaCard
import io.github.vkindl.nbaplayers.core.designsystem.component.NbaTopAppBar
import io.github.vkindl.nbaplayers.core.designsystem.component.NbaImage
import io.github.vkindl.nbaplayers.core.designsystem.theme.NbaTheme
import io.github.vkindl.nbaplayers.core.domain.model.Team
import io.github.vkindl.nbaplayers.feature.team.ui.TeamViewModel.TeamUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun TeamScreen(
    viewModel: TeamViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Content(
        state = state,
        onBackClick = onBackClick
    )
}

@Composable
private fun Content(
    state: TeamUiState,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            NbaTopAppBar(
                title = { Text(text = stringResource(id = R.string.team_screen_title)) },
                navigationIcon = { BackIconButton(onClick = onBackClick) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (state) {
                is TeamUiState.Loading -> FullScreenLoadingIndicator()
                is TeamUiState.Success -> TeamContent(team = state.team)
                is TeamUiState.Error -> ErrorContent(errorMessage = state.errorMessage)
            }
        }
    }
}

@Composable
private fun TeamContent(
    modifier: Modifier = Modifier,
    team: Team
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        NbaImage(
            modifier = Modifier.size(120.dp),
            logoResId = team.toLogoResId(),
            contentDescription = null
        )
        Text(
            text = team.name,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.size(16.dp))
        NbaCard(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                KeyValueRow(
                    label = stringResource(id = R.string.team_screen_conference),
                    value = team.conference
                )
                HorizontalDivider()
                KeyValueRow(
                    label = stringResource(id = R.string.team_screen_division),
                    value = team.division
                )
                HorizontalDivider()
                KeyValueRow(
                    label = stringResource(id = R.string.team_screen_city),
                    value = team.city
                )
            }
        }
    }
}

@Composable
@Preview
private fun TeamScreenPreview() {
    NbaTheme {
        Content(
            state = TeamUiState.Success(
                Team(
                    id = 1,
                    abbreviation = "LAL",
                    city = "Los Angeles",
                    conference = "West",
                    division = "Pacific",
                    name = "Los Angeles Lakers"
                )
            ),
            onBackClick = {}
        )
    }
}
