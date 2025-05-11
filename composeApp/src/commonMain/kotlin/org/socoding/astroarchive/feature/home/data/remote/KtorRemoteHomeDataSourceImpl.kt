package org.socoding.astroarchive.feature.home.data.remote

import io.ktor.client.HttpClient
import org.socoding.astroarchive.core.data.remote.dto.ApodDto
import org.socoding.astroarchive.core.data.remote.safeCall
import org.socoding.astroarchive.core.domain.DataError
import org.socoding.astroarchive.core.domain.Result
import io.ktor.client.request.get
import io.ktor.client.request.parameter

const val BASE_URL = "https://api.nasa.gov/"

class KtorRemoteHomeDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteHomeDataSource {
    override suspend fun getApod(): Result<ApodDto, DataError.Remote> {
        return safeCall<ApodDto> {
            httpClient.get(
                urlString = "${BASE_URL}planetary/apod"
            ) {
                parameter("api_key", "t2nCo23bm1o9C8wvf6Sb7cdYCp0S47bCkEInlaNq")
            }
        }
    }
}