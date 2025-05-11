package org.socoding.astroarchive.feature.savedmedia.presentation

import org.socoding.astroarchive.core.domain.model.MediaItem

sealed interface SavedMediaAction {
    object UpdateMediaSavedStatus: SavedMediaAction
    data class OnMediaItemClick(val mediaItem: MediaItem) : SavedMediaAction
}