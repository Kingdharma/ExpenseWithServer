package data.repository

import coreNetwork.service.MovieApiService
import details.model.MovieDetails
import details.repository.DetailsRepository

class DetailsRepoImpl(private val service: MovieApiService) : DetailsRepository {
    override suspend fun getMovieDetails(id: String): Result<MovieDetails> {
     return service.getMovieDetails(id).map {
         data ->
         MovieDetails(
             id= data.id.toString(),
             title = data.title,
             overView = data.overview,
             imageUrl = buildImageUrl(data.posterPath?:"")
         )
     }
    }
    private fun buildImageUrl(path: String): String {
        return if (path.isEmpty()) " " else "https://image.tmdb.org/t/p/original/$path"

    }
}