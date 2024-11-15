package io.github.vkindl.nbaplayers.ui.playerdetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PlayerDetailScreen(
    onBackClick: () -> Unit,
    onTeamClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onTeamClick)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Player detail")
    }
}
