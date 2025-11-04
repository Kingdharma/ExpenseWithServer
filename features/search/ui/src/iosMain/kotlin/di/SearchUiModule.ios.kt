package di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module
import ui.SearchViewModel

private val viewModelModule = module {
    factory { SearchViewModel(get()) }
}
actual fun getSearchUiModule(): Module {
    return viewModelModule
}

class SearchModelProvider : KoinComponent{
    fun provideSearchViewModel(): SearchViewModel = get()
}