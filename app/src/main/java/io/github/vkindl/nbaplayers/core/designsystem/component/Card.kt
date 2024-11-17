package io.github.vkindl.nbaplayers.core.designsystem.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.vkindl.nbaplayers.core.designsystem.theme.NbaTheme

@Composable
fun NbaElevatedCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        content = content
    )
}

@Preview
@Composable
private fun NbaElevatedCardPreview() {
    NbaTheme {
        NbaElevatedCard {
            Text(text = "Elevated Card")
        }
    }
}
