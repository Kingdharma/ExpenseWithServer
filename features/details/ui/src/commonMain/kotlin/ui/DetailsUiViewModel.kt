package ui

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import details.useCases.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class DetailsUiViewModel(private val detailsUseCase: GetMovieDetailsUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(viewModelScope, DetailsUIState())
    @NativeCoroutinesState
     val uiState : StateFlow<DetailsUIState> =_uiState
    fun getMovies(id: String) {
        viewModelScope.launch {
            _uiState.update { DetailsUIState(isLoading = true) }
            detailsUseCase.execute(id)
                .onSuccess { data ->
                    _uiState.update { DetailsUIState(data = data) }
                }.onFailure { error ->
                    _uiState.update { DetailsUIState(error = error.toString()) }
                }
        }
    }
}