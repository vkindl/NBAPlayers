package io.github.vkindl.nbaplayers.ui.players

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PlayersScreen(
    onPlayerClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onPlayerClick)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Players")
    }
}
