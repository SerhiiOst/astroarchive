package org.socoding.astroarchive.feature.home.presentation

import org.socoding.astroarchive.core.domain.model.Apod


data class HomeState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val apod: Apod? = null
)
