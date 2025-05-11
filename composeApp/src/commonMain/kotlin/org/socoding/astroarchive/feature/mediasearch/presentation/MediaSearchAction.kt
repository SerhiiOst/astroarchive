package org.socoding.astroarchive.feature.mediasearch.presentation

import org.socoding.astroarchive.core.domain.model.MediaItem

sealed interface MediaSearchAction {
    data class OnQueryChange(val query: String) : MediaSearchAction
    data class OnMediaItemClick(val mediaItem: MediaItem) : MediaSearchAction
    object OnSaveClick : MediaSearchAction
}