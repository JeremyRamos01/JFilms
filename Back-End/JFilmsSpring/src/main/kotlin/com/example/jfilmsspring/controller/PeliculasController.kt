package com.example.jfilmsspring.controller

import com.example.jfilmsspring.config.APIConfig
import com.example.jfilmsspring.dto.*

import com.example.jfilmsspring.errorsResult.ButacasError
import com.example.jfilmsspring.errorsResult.EntradasError
import com.example.jfilmsspring.errorsResult.PeliculasError
import com.example.jfilmsspring.mappers.toDto
import com.example.jfilmsspring.models.Peliculas
import com.example.jfilmsspring.repositories.EntradasRepository
import com.example.jfilmsspring.repositories.PeliculasRepository
import com.example.jfilmsspring.services.users.PeliculasService
import com.example.jfilmsspring.services.users.UsuarioServices
import com.github.michaelbull.result.mapBoth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.json.GsonJsonParser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*

@RestController
@RequestMapping(APIConfig.API_PATH + "/peliculas")
class PeliculasController
@Autowired constructor(
    private val  service: PeliculasService

) {
    @GetMapping("/todas")
        suspend fun obtenerPeliculas( ): EsqueletoMovieDB {
        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
            .header("accept", "application/json")
            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YTlmMzIzZWZjYzk0NDZkZDQ0NGRkMzczMzBjNGZkZSIsInN1YiI6IjY0MzkyNGUwNGE1MmY4MDBmNGY3Y2FiOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.dy4YzD2p9D_EmmE6bSq81fzlCd1S8j4CVXQfCTInW_c")
            .uri(URI.create("https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1"))
            .build()
        val response = withContext(Dispatchers.IO) {
            client.send(request, HttpResponse.BodyHandlers.ofString())
        };
        val pelis: EsqueletoMovieDB = Json.decodeFromString(response.body())
        println(pelis.results)
        pelis.results.forEach { x ->
            run {
                service.save(Peliculas(title = x.originalTitle, overview = x.overview, poster_path = x.posterPath, vote_average = x.voteAverage))
            }
        }
    return  pelis
    }

    @GetMapping("")
    suspend fun findAll(): ResponseEntity<List<PeliculasDTO>> {
        val res = service.findAll()
            .toList().map {
                it.toDto()
            }
        return ResponseEntity.ok(res)

    }

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: Int): ResponseEntity<PeliculasDTO> {
        service.findById(id).mapBoth(
            success = {
                return ResponseEntity.ok(
                    it.toDto()
                )
            },
            failure = { return handleErrors(it) }
        )
    }

    private fun handleErrors(peliculasError: PeliculasError): ResponseEntity<PeliculasDTO> {
        when (peliculasError) {
            is PeliculasError.NotFound -> throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                peliculasError.message
            )

            is PeliculasError.BadRequest -> throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                peliculasError.message
            )
        }
    }
}