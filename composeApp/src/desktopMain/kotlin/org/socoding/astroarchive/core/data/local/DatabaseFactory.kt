package org.socoding.astroarchive.core.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<MediaDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "AstroArchive")
            os.contains("mac") -> File(userHome, "Library/Application Support/AstroArchive")
            else -> File(userHome, ".local/share/AstroArchive")
        }

        if(!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, MediaDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}