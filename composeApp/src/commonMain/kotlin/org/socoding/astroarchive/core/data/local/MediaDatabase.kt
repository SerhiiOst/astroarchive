package org.socoding.astroarchive.core.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.socoding.astroarchive.core.data.local.entity.MediaItemEntity

@Database(
    entities = [MediaItemEntity::class],
    version = 1
)
@TypeConverters(
    StringListTypeConverter::class
)
@ConstructedBy(MediaDatabaseConstructor::class)
abstract class MediaDatabase: RoomDatabase() {
    abstract val mediaDao: MediaDao

    companion object {
        const val DB_NAME = "media.db"
    }
}