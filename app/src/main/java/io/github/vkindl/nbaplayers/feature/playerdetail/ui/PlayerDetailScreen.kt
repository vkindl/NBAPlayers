package io.github.vkindl.nbaplayers.feature.playerdetail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
import io.github.vkindl.nbaplayers.core.designsystem.component.NbaImage
import io.github.vkindl.nbaplayers.core.designsystem.component.NbaTopAppBar
import io.github.vkindl.nbaplayers.core.designsystem.theme.NbaTheme
import io.github.vkindl.nbaplayers.core.domain.model.Player
import io.github.vkindl.nbaplayers.core.domain.model.Team
import io.github.vkindl.nbaplayers.feature.playerdetail.ui.PlayerDetailViewModel.PlayerDetailUiState
import io.github.vkindl.nbaplayers.feature.team.ui.toLogoResId
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayerDetailScreen(
    viewModel: PlayerDetailViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onTeamClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Content(
        state = state,
        onTeamClick = onTeamClick,
        onBackClick = onBackClick
    )
}

@Composable
private fun Content(
    state: PlayerDetailUiState,
    onTeamClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            NbaTopAppBar(
                title = { Text(text = stringResource(id = R.string.player_detail_screen_title)) },
                navigationIcon = { BackIconButton(onClick = onBackClick) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            when (state) {
                is PlayerDetailUiState.Success -> PlayerDetailContent(
                    player = state.player,
                    onTeamClick = onTeamClick
                )
                is PlayerDetailUiState.Loading -> FullScreenLoadingIndicator()
                is PlayerDetailUiState.Error -> ErrorContent(errorMessage = state.errorMessage)
            }
        }
    }
}

@Composable
private fun PlayerDetailContent(
    modifier: Modifier = Modifier,
    player: Player,
    onTeamClick: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = player.fullName,
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = stringResource(id = R.string.player_detail_screen_player_information),
            style = MaterialTheme.typography.titleMedium
        )
        PlayerInformationCard(
            player = player
        )
        Text(
            text = stringResource(id = R.string.player_detail_screen_team_information),
            style = MaterialTheme.typography.titleMedium
        )
        TeamInformationCard(
            team = player.team,
            onTeamClick = onTeamClick
        )
    }
}

@Composable
private fun PlayerInformationCard(
    modifier: Modifier = Modifier,
    player: Player
) {
    NbaCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            KeyValueRow(
                label = stringResource(id = R.string.player_detail_screen_number),
                value = player.jerseyNumber
            )
            HorizontalDivider()
            KeyValueRow(
                label = stringResource(id = R.string.player_detail_screen_position),
                value = player.position
            )
            HorizontalDivider()
            KeyValueRow(
                label = stringResource(id = R.string.player_detail_screen_nationality),
                value = player.country
            )
            HorizontalDivider()
            KeyValueRow(
                label = stringResource(id = R.string.player_detail_screen_height),
                value = stringResource(
                    id = R.string.player_detail_screen_height_value,
                    player.height
                )
            )
            HorizontalDivider()
            KeyValueRow(
                label = stringResource(id = R.string.player_detail_screen_weight),
                value = stringResource(
                    id = R.string.player_detail_screen_weight_value,
                    player.weight
                )
            )
            HorizontalDivider()
            KeyValueRow(
                label = stringResource(id = R.string.player_detail_screen_draft_year),
                value = player.draftYear
            )
        }
    }
}

@Composable
private fun TeamInformationCard(
    modifier: Modifier = Modifier,
    team: Team,
    onTeamClick: (Int) -> Unit
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        onClick = { onTeamClick(team.id) }
    ) {
        ListItem(
            leadingContent = {
                NbaImage(
                    modifier = Modifier.size(48.dp),
                    logoResId = team.toLogoResId()
                )
            },
            headlineContent = {
                Text(
                    text = team.name,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            trailingContent = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chevron_right),
                    contentDescription = null
                )
            }
        )
    }
}

@Composable
@Preview
private fun PlayerDetailScreenPreview() {
    NbaTheme {
        Content(
            state = PlayerDetailUiState.Success(
                player = Player(
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
            ),
            onBackClick = {},
            onTeamClick = {}
        )
    }
}
