package org.socoding.astroarchive.core.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import astroarchive.composeapp.generated.resources.Res
import astroarchive.composeapp.generated.resources.ic_filled_bookmark
import astroarchive.composeapp.generated.resources.ic_outline_bookmark
import org.jetbrains.compose.resources.painterResource
import org.socoding.astroarchive.core.presentation.component.ScalableImage

@Composable
fun MediaDetailsScreen(
    imageLink: String,
    title: String,
    description: String,
    isSaved: Boolean?,
    isSavable: Boolean,
    isLoading: Boolean,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    val safeDrawingPaddingVales = WindowInsets.safeDrawing.asPaddingValues()

    Box {
        ScalableImage(imageLink)
        Box(
            modifier = Modifier
                .padding(top = safeDrawingPaddingVales.calculateTopPadding())
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 16.dp)
                .align(Alignment.TopCenter)
        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null
                )
            }
            if (isSavable)
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    enabled = !isLoading,
                    onClick = onSaveClick
                ) {
                    val iconRes = if (isSaved == true)
                        Res.drawable.ic_filled_bookmark
                    else
                        Res.drawable.ic_outline_bookmark
                    Icon(
                        painter = painterResource(iconRes),
                        contentDescription = null
                    )
                }
        }
    }
}