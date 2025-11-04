package di

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ui.SearchViewModel

actual fun getSearchUiModule(): Module {
  return module{
      viewModel { SearchViewModel(get()) }
  }
}