package org.socoding.astroarchive.feature.mediasearch.presentation

import org.socoding.astroarchive.core.domain.model.MediaItem

data class MediaSearchState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val items: List<MediaItem?> = emptyList(),
    val searchQuery: String = "",
    val selectedMediaItem: MediaItem? = null
)
