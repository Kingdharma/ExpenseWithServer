package ui

import domain.model.Movie

data class SearchUiState (
    val isLoading:Boolean = false,
    val error: String ="",
    val data: List<Movie>? = null
)