package org.socoding.astroarchive.feature.mediasearch.data.repository

import androidx.sqlite.SQLiteException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.socoding.astroarchive.core.data.local.MediaDao
import org.socoding.astroarchive.core.data.local.entity.MediaItemEntity
import org.socoding.astroarchive.core.data.mappers.toMediaItem
import org.socoding.astroarchive.core.data.mappers.toMediaItemEntity
import org.socoding.astroarchive.core.domain.DataError
import org.socoding.astroarchive.core.domain.EmptyResult
import org.socoding.astroarchive.core.domain.Result
import org.socoding.astroarchive.core.domain.map
import org.socoding.astroarchive.core.domain.model.MediaItem
import org.socoding.astroarchive.core.domain.model.SearchResult
import org.socoding.astroarchive.feature.mediasearch.data.remote.RemoteMediaSearchDataSource
import org.socoding.astroarchive.feature.mediasearch.domain.repository.MediaSearchRepository

class MediaSearchRepositoryImpl(
    private val remoteDataSource: RemoteMediaSearchDataSource,
    private val mediaDao: MediaDao
) : MediaSearchRepository {
    override suspend fun searchMedia(
        query: String,
        page: Int
    ): Result<SearchResult, DataError.Remote> {
        return remoteDataSource
            .searchMedia(query, page)
            .map { dto ->
                val items = dto.collection?.items?.map { itemDto ->
                    itemDto.toMediaItem()
                }
                SearchResult(
                    items = items,
                    nextLink = dto.collection?.links?.firstOrNull()?.href
                )
            }
    }

    override fun isMediaItemSaved(id: String): Flow<Boolean> {
        return mediaDao
            .getSavedMedia()
            .map { mediaItems ->
                mediaItems.any { it.id == id }
            }
    }

    override suspend fun saveMediaItem(mediaItem: MediaItem): EmptyResult<DataError.Local> {
        return try {
            mediaDao.upsert(mediaItem.toMediaItemEntity())
            Result.Success(Unit)
        } catch(e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun removeMediaItem(id: String) {
        mediaDao.removeSavedMediaItem(id)
    }
}