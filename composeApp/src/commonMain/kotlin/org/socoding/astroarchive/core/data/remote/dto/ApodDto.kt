package org.socoding.astroarchive.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Astronomy Picture of the Day
@Serializable
data class ApodDto(
    val date: String? = null,
    val title: String? = null,
    val explanation: String? = null,
    val url: String? = null,
    @SerialName("hdurl") val hdUrl: String? = null,
    @SerialName("media_type") val mediaType: String? = null
)