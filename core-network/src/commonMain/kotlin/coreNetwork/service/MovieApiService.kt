package coreNetwork.service

import coreNetwork.model.MovieDTO
import coreNetwork.model.MovieDetailsResponse
import coreNetwork.model.SearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieApiService(private  val client: HttpClient) {

    suspend fun search(query: String): Result<List<MovieDTO>>{
        return  try {
            val searchResponse= client.get("3/search/movie"){
                 parameter("query",query)
             }.body<SearchResponse>()
            Result.success(searchResponse.results)
         }
         catch (e: Exception){
             Result.failure(e)
         }
    }

    suspend fun getMovieDetails(id: String) : Result<MovieDetailsResponse>{
       return try{
         val movieDetailsResponse=  client.get("3/movie/${id}").body<MovieDetailsResponse>()
            Result.success(movieDetailsResponse)
        }
        catch (e: Exception){
            Result.failure(e)
        }
    }
}