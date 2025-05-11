package org.socoding.astroarchive.feature.mediasearch.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.socoding.astroarchive.core.domain.model.MediaItem

@Composable
fun MediaImageItem(
    mediaItem: MediaItem,
    modifier: Modifier = Modifier
) {
    val colorStops = arrayOf(
        0.0f to Color.Transparent,
        0.5f to Color.Black.copy(alpha = 0.8f)
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            //TODO implement image link picking logic
            model = mediaItem.previewLink,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.verticalGradient(colorStops = colorStops))
                .padding(8.dp)
                .align(Alignment.BottomCenter),
            text = mediaItem.title ?: "",
            maxLines = 2,
            fontSize = 14.sp,
            color = Color.White
        )
    }
}