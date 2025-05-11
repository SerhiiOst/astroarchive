package org.socoding.astroarchive.core.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDto(
    val collection: SearchResponseCollectionDto? = null
)

@Serializable
data class SearchResponseCollectionDto(
    val metadata: MetadataDto? = null,
    val items: List<MediaItemDto>? = null,
    val version: String? = null,
    val href: String? = null,
    val links: List<LinkDto> = emptyList()
)

@Serializable
data class MetadataDto(
    @SerialName("total_hits") val totalHits: Int? = null
)

@Serializable
data class LinkDto(
    val rel: String? = null,
    val prompt: String? = null,
    val href: String? = null
)