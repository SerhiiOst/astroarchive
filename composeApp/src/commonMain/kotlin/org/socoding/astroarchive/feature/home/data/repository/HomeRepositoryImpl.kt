package org.socoding.astroarchive.feature.home.data.repository

import org.socoding.astroarchive.core.domain.DataError
import org.socoding.astroarchive.core.domain.Result
import org.socoding.astroarchive.core.domain.map
import org.socoding.astroarchive.core.domain.model.Apod
import org.socoding.astroarchive.feature.home.data.remote.RemoteHomeDataSource
import org.socoding.astroarchive.feature.home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val remoteDataSource: RemoteHomeDataSource
) : HomeRepository {
    override suspend fun getApod(): Result<Apod, DataError.Remote> {
        return remoteDataSource
            .getApod()
            .map { dto ->
                Apod(
                    date = dto.date,
                    title = dto.title,
                    explanation = dto.explanation,
                    url = dto.url,
                    hdUrl = dto.hdUrl,
                    mediaType = dto.mediaType
                )
            }
    }
}