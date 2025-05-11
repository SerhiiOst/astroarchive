package org.socoding.astroarchive.core.domain.model

data class MediaItem(
    val id: String? = null,
    val mediaType: String? = null,
    val title: String? = null,
    val description: String? = null,
    val dateCreated: String? = null,
    val href: String? = null,
    val keywords: List<String>? = null,
    val previewLink: String? = null,
    val hdLink: String? = null,
    val isSaved: Boolean = false
)