package io.github.vkindl.nbaplayers.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import io.github.vkindl.nbaplayers.R
import io.github.vkindl.nbaplayers.core.designsystem.theme.NbaTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NbaImage(
    modifier: Modifier = Modifier,
    @DrawableRes logoResId: Int,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null
) {
    GlideImage(
        modifier = modifier,
        model = logoResId,
        loading = placeholder(R.drawable.ic_image_placeholder),
        contentScale = contentScale,
        contentDescription = contentDescription
    )
}

@Preview(showBackground = true)
@Composable
private fun NbaImagePreview() {
    NbaTheme {
        NbaImage(logoResId = R.drawable.ic_atlanta_hawks)
    }
}
