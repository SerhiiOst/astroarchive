package org.socoding.astroarchive.feature.home.domain.repository

import org.socoding.astroarchive.core.domain.DataError
import org.socoding.astroarchive.core.domain.Result
import org.socoding.astroarchive.core.domain.model.Apod

interface HomeRepository {
    suspend fun getApod(): Result<Apod, DataError.Remote>
}