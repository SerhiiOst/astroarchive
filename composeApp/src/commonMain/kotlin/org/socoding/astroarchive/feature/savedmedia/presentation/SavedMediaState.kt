package org.socoding.astroarchive.feature.savedmedia.presentation

import org.socoding.astroarchive.core.domain.model.MediaItem

data class SavedMediaState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val items: List<MediaItem> = emptyList(),
    val selectedMediaItem: MediaItem? = null
)
