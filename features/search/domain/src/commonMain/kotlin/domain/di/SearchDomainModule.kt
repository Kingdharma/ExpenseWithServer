package domain.di

import domain.usecase.SearchUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun getSearchDomainModule() : Module{
    return module{
        factory { SearchUseCase(get()) }
    }
}