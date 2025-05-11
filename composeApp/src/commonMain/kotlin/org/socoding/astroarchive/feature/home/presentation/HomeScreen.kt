package org.socoding.astroarchive.feature.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import astroarchive.composeapp.generated.resources.Res
import astroarchive.composeapp.generated.resources.ic_filled_bookmark
import org.jetbrains.compose.resources.painterResource
import org.socoding.astroarchive.feature.home.presentation.component.MediaSearchBar

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel = koinViewModel(),
    onSearchClick: () -> Unit,
    onBookmarksClick: () -> Unit
) {
    val state: HomeState by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        onSearchClick = onSearchClick,
        onBookmarksClick = onBookmarksClick,
        state = state
    )
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    state: HomeState,
) {
    val safeDrawingPaddingVales = WindowInsets.safeDrawing.asPaddingValues()

    Box {
        AsyncImage(
            modifier = modifier.fillMaxSize(),
            model = state.apod?.hdUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Row(
            modifier = Modifier
                .padding(safeDrawingPaddingVales)
                .padding(16.dp)
        )
        {
            MediaSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                onSearchClick()
            }
            Box(
                modifier = modifier
                    .padding(start = 16.dp)
                    .size(60.dp)
                    .alpha(0.8f)
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { onBookmarksClick() }
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(Res.drawable.ic_filled_bookmark),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        val colorStops = arrayOf(
            0.0f to Color.Transparent,
            0.3f to Color.Black
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Brush.verticalGradient(colorStops = colorStops))
                .alpha(0.8f)
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp)
        ) {
            Spacer(modifier = Modifier.fillMaxWidth().height(50.dp))
            Text(
                text = "Astronomy Picture of the Day:",
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = state.apod?.title ?: "",
                color = Color.White,
                lineHeight = 1.1.em,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = state.apod?.explanation ?: "",
                color = Color.White,
                fontSize = 14.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}