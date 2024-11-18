package io.github.vkindl.nbaplayers.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.vkindl.nbaplayers.core.designsystem.theme.NbaTheme

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    errorMessage: String
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = errorMessage)
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorContentPreview() {
    NbaTheme {
        ErrorContent(errorMessage = "Error")
    }
}
