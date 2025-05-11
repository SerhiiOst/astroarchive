package org.socoding.astroarchive.core.domain.model

// Astronomy Picture of the Day
data class Apod(
    val date: String? = null,
    val title: String? = null,
    val explanation: String? = null,
    val url: String? = null,
    val hdUrl: String? = null,
    val mediaType: String? = null
)