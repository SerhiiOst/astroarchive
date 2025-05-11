package org.socoding.astroarchive.feature.mediasearch.domain.repository

import kotlinx.coroutines.flow.Flow
import org.socoding.astroarchive.core.data.local.entity.MediaItemEntity
import org.socoding.astroarchive.core.domain.DataError
import org.socoding.astroarchive.core.domain.EmptyResult
import org.socoding.astroarchive.core.domain.model.SearchResult
import org.socoding.astroarchive.core.domain.Result
import org.socoding.astroarchive.core.domain.model.MediaItem

interface MediaSearchRepository {
    suspend fun searchMedia(query: String, page: Int = 1): Result<SearchResult, DataError.Remote>

    fun isMediaItemSaved(id: String): Flow<Boolean>
    suspend fun saveMediaItem(mediaItem: MediaItem): EmptyResult<DataError.Local>
    suspend fun removeMediaItem(id: String)
}