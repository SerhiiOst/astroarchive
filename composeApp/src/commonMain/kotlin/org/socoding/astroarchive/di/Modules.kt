package org.socoding.astroarchive.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.koin.core.module.Module
import org.koin.dsl.module
import org.socoding.astroarchive.core.data.remote.HttpClientFactory
import org.socoding.astroarchive.feature.home.data.remote.RemoteHomeDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.socoding.astroarchive.feature.home.data.remote.KtorRemoteHomeDataSourceImpl
import org.socoding.astroarchive.feature.home.data.repository.HomeRepositoryImpl
import org.socoding.astroarchive.feature.home.domain.repository.HomeRepository
import org.socoding.astroarchive.feature.home.presentation.HomeViewModel
import org.socoding.astroarchive.feature.mediasearch.data.remote.KtorRemoteMediaSearchDataSource
import org.socoding.astroarchive.feature.mediasearch.data.remote.RemoteMediaSearchDataSource
import org.socoding.astroarchive.feature.mediasearch.data.repository.MediaSearchRepositoryImpl
import org.socoding.astroarchive.feature.mediasearch.domain.repository.MediaSearchRepository
import org.socoding.astroarchive.core.data.local.DatabaseFactory
import org.socoding.astroarchive.core.data.local.MediaDatabase
import org.socoding.astroarchive.feature.mediasearch.presentation.MediaSearchViewModel
import org.socoding.astroarchive.feature.savedmedia.data.repository.SavedMediaRepositoryImpl
import org.socoding.astroarchive.feature.savedmedia.domain.repository.SavedMediaRepository
import org.socoding.astroarchive.feature.savedmedia.presentation.SavedMediaViewModel

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }

    singleOf(::KtorRemoteHomeDataSourceImpl).bind<RemoteHomeDataSource>()
    singleOf(::HomeRepositoryImpl).bind<HomeRepository>()
    singleOf(::KtorRemoteMediaSearchDataSource).bind<RemoteMediaSearchDataSource>()
    singleOf(::MediaSearchRepositoryImpl).bind<MediaSearchRepository>()
    singleOf(::SavedMediaRepositoryImpl).bind<SavedMediaRepository>()

    viewModelOf(::HomeViewModel)
    viewModelOf(::MediaSearchViewModel)
    viewModelOf(::SavedMediaViewModel)

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<MediaDatabase>().mediaDao }
}