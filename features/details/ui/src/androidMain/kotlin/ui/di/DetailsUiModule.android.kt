package ui.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ui.DetailsUiViewModel

actual fun getDetailsUiModule(): Module {
return module {
    viewModel { DetailsUiViewModel(get()) }
}
}