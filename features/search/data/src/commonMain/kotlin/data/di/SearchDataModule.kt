package data.di

import data.repository.SearchRepoImpl
import domain.repository.SearchRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun getSearchDataModule(): Module{
    return module {
        single<SearchRepository>{ SearchRepoImpl(get()) }
    }
}