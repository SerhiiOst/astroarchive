package org.socoding.astroarchive.feature.savedmedia.domain.repository

import kotlinx.coroutines.flow.Flow
import org.socoding.astroarchive.core.domain.DataError
import org.socoding.astroarchive.core.domain.EmptyResult
import org.socoding.astroarchive.core.domain.model.MediaItem

interface SavedMediaRepository {
    fun getSavedMedia(): Flow<List<MediaItem>>
    suspend fun saveMediaItem(mediaItem: MediaItem): EmptyResult<DataError.Local>
    suspend fun removeMediaItem(id: String): EmptyResult<DataError.Local>
}