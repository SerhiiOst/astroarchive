package org.socoding.astroarchive.core.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.socoding.astroarchive.core.data.local.entity.MediaItemEntity

@Dao
interface MediaDao {
    @Upsert
    suspend fun upsert(mediaItem: MediaItemEntity)

    @Query("SELECT * FROM MediaItemEntity")
    fun getSavedMedia(): Flow<List<MediaItemEntity>>

    @Query("SELECT * FROM MediaItemEntity WHERE id = :id")
    suspend fun getSavedMediaItem(id: String): MediaItemEntity?

    @Query("DELETE FROM MediaItemEntity WHERE id = :id")
    suspend fun removeSavedMediaItem(id: String)
}