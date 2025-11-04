package coreNetwork.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.serialization.kotlinx.json.json

import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
object HttpClientFactory {
    fun getInstance(): HttpClient{
        return HttpClient{
            install(ContentNegotiation){
                json(json = Json{
                    prettyPrint=true
                    ignoreUnknownKeys=true
                })
            }
            install(HttpTimeout){
                requestTimeoutMillis=3000
                socketTimeoutMillis =3000
                connectTimeoutMillis=3000

            }
//Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmYTExZGM3ZGQ1ZTc1YmExNzJkNDJjY2M5NzQ5YjljNSIsIm5iZiI6MTc2MTgxNDEyNi4zODQsInN1YiI6IjY5MDMyNjZlZjY5ZTc2ZjlkNDM0Y2NmYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Q2Vz2sjQxcBbuuWiya_2lEfsF34x7AU9mR_HgVOHa2I
//            https://api.themoviedb.org/3/search/movie
            install(DefaultRequest){
                url{
                    host="api.themoviedb.org"
                    protocol = URLProtocol.HTTPS
                }
                header(HttpHeaders.Authorization,
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmYTExZGM3ZGQ1ZTc1YmExNzJkNDJjY2M5NzQ5YjljNSIsIm5iZiI6MTc2MTgxNDEyNi4zODQsInN1YiI6IjY5MDMyNjZlZjY5ZTc2ZjlkNDM0Y2NmYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Q2Vz2sjQxcBbuuWiya_2lEfsF34x7AU9mR_HgVOHa2I")
            }
        }
    }
}