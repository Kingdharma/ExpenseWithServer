package data.di

import data.repository.DetailsRepoImpl
import details.repository.DetailsRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDetailsDataModule(): Module{
    return module {
        single<DetailsRepository>{ DetailsRepoImpl(get()) }
    }
}