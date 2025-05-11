package org.socoding.astroarchive.feature.savedmedia.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.socoding.astroarchive.core.domain.model.MediaItem
import org.socoding.astroarchive.feature.mediasearch.presentation.MediaImageItem

@Composable
fun SavedMediaScreenRoot(
    viewModel: SavedMediaViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onMediaItemClick: (MediaItem) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SavedMediaScreen(
        state = state,
        onAction = {
            when (it) {
                is SavedMediaAction.OnMediaItemClick -> onMediaItemClick(it.mediaItem)
                else -> {}
            }
            viewModel.onAction(it)
        },
        onBackClick = onBackClick
    )
}

@Composable
fun SavedMediaScreen(
    modifier: Modifier = Modifier,
    state: SavedMediaState,
    onAction: (SavedMediaAction) -> Unit,
    onBackClick: () -> Unit
) {
    val safeDrawingPaddingVales = WindowInsets.safeDrawing.asPaddingValues()

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(top = safeDrawingPaddingVales.calculateTopPadding())
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text("Saved Media")
        }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.items) {
                MediaImageItem(
                    mediaItem = it,
                    modifier = Modifier
                        .clickable { onAction(SavedMediaAction.OnMediaItemClick(it)) }
                )
            }
        }
    }
}