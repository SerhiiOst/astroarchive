package org.socoding.astroarchive.feature.home.data.remote

import org.socoding.astroarchive.core.data.remote.dto.ApodDto
import org.socoding.astroarchive.core.domain.DataError
import org.socoding.astroarchive.core.domain.Result

interface RemoteHomeDataSource {
    suspend fun getApod(): Result<ApodDto, DataError.Remote>
}