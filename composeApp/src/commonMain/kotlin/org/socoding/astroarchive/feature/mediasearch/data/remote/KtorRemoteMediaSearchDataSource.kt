package org.socoding.astroarchive.feature.mediasearch.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.socoding.astroarchive.core.data.remote.dto.SearchResponseDto
import org.socoding.astroarchive.core.data.remote.safeCall
import org.socoding.astroarchive.core.domain.DataError
import org.socoding.astroarchive.core.domain.Result

private const val BASE_IMAGE_URL = "https://images-api.nasa.gov/"

class KtorRemoteMediaSearchDataSource(
    private val httpClient: HttpClient
): RemoteMediaSearchDataSource {
    override suspend fun searchMedia(query: String, page: Int): Result<SearchResponseDto, DataError.Remote> {
        return safeCall<SearchResponseDto> {
            httpClient.get(
                urlString = "${BASE_IMAGE_URL}search"
            ) {
                parameter("q", query)
//                parameter("page", page)
            }
        }
    }
}