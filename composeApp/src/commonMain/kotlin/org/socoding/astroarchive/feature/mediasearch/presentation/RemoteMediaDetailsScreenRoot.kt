package org.socoding.astroarchive.feature.mediasearch.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.socoding.astroarchive.core.presentation.MediaDetailsScreen


@Composable
fun RemoteMediaDetailsScreenRoot(
    viewModel: MediaSearchViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val state: MediaSearchState by viewModel.state.collectAsStateWithLifecycle()

    MediaDetailsScreen(
        imageLink = state.selectedMediaItem?.hdLink ?: "",
        title = state.selectedMediaItem?.title ?: "",
        description = state.selectedMediaItem?.description ?: "",
        isSaved = state.selectedMediaItem?.isSaved,
        isSavable = true,
        isLoading = state.isLoading,
        onBackClick = onBackClick,
        onSaveClick = {
            viewModel.onAction(MediaSearchAction.OnSaveClick)
        }
    )
}