package ui

import details.model.MovieDetails

data class DetailsUIState(
    val isLoading: Boolean = false,
    val error: String ="",
    val data: MovieDetails? =null
)
