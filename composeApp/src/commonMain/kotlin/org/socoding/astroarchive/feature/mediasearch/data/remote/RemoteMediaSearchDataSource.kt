package org.socoding.astroarchive.feature.mediasearch.data.remote

import org.socoding.astroarchive.core.data.remote.dto.SearchResponseDto
import org.socoding.astroarchive.core.domain.DataError
import org.socoding.astroarchive.core.domain.Result

interface RemoteMediaSearchDataSource {
    suspend fun searchMedia(query: String, page: Int): Result<SearchResponseDto, DataError.Remote>
}