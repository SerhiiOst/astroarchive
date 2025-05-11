package org.socoding.astroarchive.feature.mediasearch.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.socoding.astroarchive.core.domain.model.MediaItem

@Composable
fun MediaSearchScreenRoot(
    viewModel: MediaSearchViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onMediaItemClick: (MediaItem) -> Unit
) {
    val state: MediaSearchState by viewModel.state.collectAsStateWithLifecycle()

    MediaSearchScreen(
        state = state,
        onAction = {
            when (it) {
                is MediaSearchAction.OnMediaItemClick -> onMediaItemClick(it.mediaItem)
                else -> {}
            }
            viewModel.onAction(it)
        },
        onBackClick = onBackClick
    )
}

@Composable
fun MediaSearchScreen(
    modifier: Modifier = Modifier,
    state: MediaSearchState,
    onAction: (MediaSearchAction) -> Unit,
    onBackClick: () -> Unit,
) {
    val safeDrawingPaddingVales = WindowInsets.safeDrawing.asPaddingValues()

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(top = safeDrawingPaddingVales.calculateTopPadding())
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton (
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null
                )
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = TextFieldDefaults.colors().copy(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                ),
                maxLines = 1,
                placeholder = {
                    Text("Search the Archive...")
                },
                value = state.searchQuery,
                onValueChange = {
                    onAction(MediaSearchAction.OnQueryChange(it))
                }
            )
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
                if (it != null)
                    MediaImageItem(
                        mediaItem = it,
                        modifier = Modifier
                            .clickable { onAction(MediaSearchAction.OnMediaItemClick(it)) }
                    )
            }
        }
    }
}