package org.socoding.astroarchive.core.data.local

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object MediaDatabaseConstructor: RoomDatabaseConstructor<MediaDatabase> {
    override fun initialize(): MediaDatabase
}