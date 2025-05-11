package org.socoding.astroarchive.core.domain.model

data class SearchResult(
    val items: List<MediaItem?>?,
    val nextLink: String? = null
)