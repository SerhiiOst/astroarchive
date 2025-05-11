package org.socoding.astroarchive.core.domain.model

data class NasaMedia(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val hdUrl: String? = null,
    val mediaType: String,
    val keywords: List<String> = emptyList()
)