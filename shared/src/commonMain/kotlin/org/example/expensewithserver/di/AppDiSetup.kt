package org.example.expensewithserver.di

import coreNetwork.di.getCoreNetworkModule
import data.di.getDetailsDataModule
import data.di.getSearchDataModule
import details.di.getDetailsDomainModule
import di.getSearchUiModule
import domain.di.getSearchDomainModule
import org.koin.core.context.startKoin
import ui.di.getDetailsUiModule

fun initKoin(){
    startKoin {
       modules(
           getCoreNetworkModule(),

           getSearchUiModule(),
           getSearchDataModule(),
           getSearchDomainModule(),

           getDetailsDataModule(),
           getDetailsUiModule(),
           getDetailsDomainModule()
       )
    }
}