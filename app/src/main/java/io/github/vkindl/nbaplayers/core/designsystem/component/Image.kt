package io.github.vkindl.nbaplayers.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

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
        contentScale = contentScale,
        contentDescription = contentDescription
    )
}
