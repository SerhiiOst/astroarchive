package org.socoding.astroarchive.core.data.mappers

import org.socoding.astroarchive.core.data.local.entity.MediaItemEntity
import org.socoding.astroarchive.core.data.remote.dto.MediaItemDto
import org.socoding.astroarchive.core.domain.model.MediaItem

fun MediaItemDto.toMediaItem(): MediaItem {
    val data = data?.firstOrNull()

    val compatibleLinks = links
        ?.filter { link ->
            link.href?.endsWith(".png") == true || link.href?.endsWith(".jpg") == true
        }
        ?.sortedBy { link -> link.size }

    val previewLink = compatibleLinks?.first()
    val hdLink = compatibleLinks?.last()

    return MediaItem(
        id = data?.nasaId,
        mediaType = data?.mediaType,
        title = data?.title,
        description = data?.description,
        dateCreated = data?.dateCreated,
        href = data?.href,
        keywords = data?.keywords,
        previewLink = previewLink?.href,
        hdLink = hdLink?.href
    )
}

fun MediaItemEntity.toMediaItem(): MediaItem {
    return MediaItem(
        id = id,
        title = title,
        description = description,
        mediaType = mediaType,
        hdLink = hdLink,
        previewLink = previewLink,
        keywords = keywords,
        dateCreated = dateCreated,
        href = href,
        isSaved = true
    )
}

fun MediaItem.toMediaItemEntity(): MediaItemEntity {
    return MediaItemEntity(
        id = id ?: "",
        title = title ?: "",
        description = description ?: "",
        hdLink = hdLink ?: "",
        previewLink = previewLink ?: "",
        mediaType = mediaType ?: "",
        keywords = keywords,
        dateCreated = dateCreated
    )
}