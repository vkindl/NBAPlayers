package io.github.vkindl.nbaplayers.feature.players.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import io.github.vkindl.nbaplayers.R
import io.github.vkindl.nbaplayers.core.designsystem.component.ErrorContent
import io.github.vkindl.nbaplayers.core.designsystem.component.FullScreenLoadingIndicator
import io.github.vkindl.nbaplayers.core.designsystem.component.LoadingIndicator
import io.github.vkindl.nbaplayers.core.designsystem.component.NbaImage
import io.github.vkindl.nbaplayers.core.designsystem.component.NbaTopAppBar
import io.github.vkindl.nbaplayers.core.domain.model.Player
import io.github.vkindl.nbaplayers.feature.team.ui.toLogoResId
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayersScreen(
    viewModel: PlayersViewModel = koinViewModel(),
    onPlayerClick: (Int) -> Unit
) {
    val items = viewModel.players.collectAsLazyPagingItems()

    Content(
        pager = items,
        onPlayerClick = onPlayerClick
    )
}

@Composable
private fun Content(
    pager: LazyPagingItems<Player>,
    onPlayerClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            NbaTopAppBar(title = {
                Text(text = stringResource(R.string.players_screen_title))
            })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val refresh = pager.loadState.refresh) {
                is LoadState.Loading -> FullScreenLoadingIndicator()
                is LoadState.Error -> {
                    ErrorContent(errorMessage = refresh.error.localizedMessage.orEmpty())
                }
                is LoadState.NotLoading -> PlayerListContent(
                    pager = pager,
                    onPlayerClick = onPlayerClick
                )
            }
        }
    }
}

@Composable
private fun PlayerListContent(
    modifier: Modifier = Modifier,
    pager: LazyPagingItems<Player>,
    onPlayerClick: (Int) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(pager.itemCount) {
            val player = pager[it]
            if (player != null) {
                PlayerListItem(
                    player = player,
                    onClick = onPlayerClick
                )
            }
        }
        if (pager.loadState.append is LoadState.Loading) {
            item {
                LoadingIndicator()
            }
        }
    }
}

@Composable
private fun PlayerListItem(
    modifier: Modifier = Modifier,
    player: Player,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = modifier.clickable(onClick = { onClick(player.id) })
    ) {
        ListItem(
            headlineContent = {
                Text(
                    text = player.fullName,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            overlineContent = {
                Text(
                    text = player.team.name,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            supportingContent = {
                Text(
                    text = stringResource(id = R.string.players_screen_position, player.position),
                    style = MaterialTheme.typography.bodySmall
                )
            },
            trailingContent = {
                NbaImage(
                    modifier = Modifier
                        .size(64.dp)
                        .align(Alignment.CenterHorizontally),
                    logoResId = player.team.toLogoResId()
                )
            }
        )
    }
    HorizontalDivider()
}
