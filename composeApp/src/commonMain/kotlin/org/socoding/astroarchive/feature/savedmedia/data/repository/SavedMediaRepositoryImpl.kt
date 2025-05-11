package org.socoding.astroarchive.feature.savedmedia.data.repository

import androidx.sqlite.SQLiteException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.socoding.astroarchive.core.data.local.MediaDao
import org.socoding.astroarchive.core.data.mappers.toMediaItem
import org.socoding.astroarchive.core.data.mappers.toMediaItemEntity
import org.socoding.astroarchive.core.domain.DataError
import org.socoding.astroarchive.core.domain.EmptyResult
import org.socoding.astroarchive.core.domain.Result
import org.socoding.astroarchive.core.domain.model.MediaItem
import org.socoding.astroarchive.feature.savedmedia.domain.repository.SavedMediaRepository

class SavedMediaRepositoryImpl(
    private val mediaDao: MediaDao
) : SavedMediaRepository {
    override fun getSavedMedia(): Flow<List<MediaItem>> {
        return mediaDao
            .getSavedMedia()
            .map {
                it.map { entity -> entity.toMediaItem() }
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

    override suspend fun removeMediaItem(id: String): EmptyResult<DataError.Local>  {
        return try {
            mediaDao.removeSavedMediaItem(id)
            Result.Success(Unit)
        } catch(e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }
}