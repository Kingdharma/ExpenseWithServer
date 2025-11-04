package details.repository

import details.model.MovieDetails

interface DetailsRepository {
    suspend fun getMovieDetails(id: String) : Result<MovieDetails>
}