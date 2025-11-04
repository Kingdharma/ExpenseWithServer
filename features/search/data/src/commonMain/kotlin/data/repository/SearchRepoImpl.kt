package data.repository

import coreNetwork.service.MovieApiService
import domain.model.Movie
import domain.repository.SearchRepository

class SearchRepoImpl(private val service: MovieApiService) :SearchRepository {
    override suspend fun search(query: String): Result<List<Movie>> {
        return service.search(query).map { list ->
            list.map { dto ->
                Movie(
                    id = dto.id.toString(),
                    title = dto.title,
                    imageUrl = buildImageUrl(dto.posterPath ?: "")
                )
            }
        }
    }

    private fun buildImageUrl(path: String): String {
        return if (path.isEmpty()) " " else "https://image.tmdb.org/t/p/original/$path"

    }
}

