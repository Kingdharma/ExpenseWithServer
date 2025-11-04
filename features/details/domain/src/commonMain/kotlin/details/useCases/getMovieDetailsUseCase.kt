package details.useCases

import details.repository.DetailsRepository

class GetMovieDetailsUseCase(private val detailsRepository: DetailsRepository) {
    suspend fun execute(id: String) =detailsRepository.getMovieDetails(id)
}