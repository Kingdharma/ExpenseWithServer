package domain.repository

import domain.model.Movie

interface SearchRepository {
    suspend fun search(query: String) : Result<List<Movie>>
}