package coreNetwork.di

import coreNetwork.client.HttpClientFactory
import coreNetwork.service.MovieApiService
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCoreNetworkModule():Module{
    return module {
        single { MovieApiService(HttpClientFactory.getInstance()) }
    }
}