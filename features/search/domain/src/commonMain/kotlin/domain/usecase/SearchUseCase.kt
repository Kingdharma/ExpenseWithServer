package domain.usecase

import domain.repository.SearchRepository

class SearchUseCase(private val searchRepository: SearchRepository)  {
    suspend fun execute(query: String) = searchRepository.search(query)
}