package org.socoding.astroarchive.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MediaItemEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    val description: String,
    val hdLink: String,
    val previewLink: String,
    val mediaType: String,
    val href: String? = null,
    val keywords: List<String>? = null,
    val dateCreated: String? = null,
)