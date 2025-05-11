package org.socoding.astroarchive.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaItemDataDto(
    @SerialName("nasa_id") val nasaId: String? = null,
    @SerialName("media_type") val mediaType: String? = null,
    val title: String? = null,
    val description: String? = null,
    @SerialName("date_created") val dateCreated: String? = null,
    val href: String? = null,
    val keywords: List<String>? = null,
)

@Serializable
data class MediaItemDto(
    val data: List<MediaItemDataDto>? = null,
    val links: List<DataLinkDto>? = null,
    val href: String? = null,
)

@Serializable
data class DataLinkDto(
    val rel: String? = null,
    val href: String? = null,
    val render: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val size: Int? = null,
)
