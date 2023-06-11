package com.example.jfilmsspring.services.users

import com.example.jfilmsspring.errorsResult.PeliculasError
import com.example.jfilmsspring.models.Peliculas
import com.example.jfilmsspring.repositories.PeliculasRepository
import com.github.michaelbull.result.*
import kotlinx.coroutines.flow.Flow
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.onSuccess

private val logger = KotlinLogging.logger {}

@Service
class PeliculasService
@Autowired constructor(
    private val repository: PeliculasRepository,
): PeliculasServiceFlows {

    override suspend fun findAll(): Flow<Peliculas> {
        logger.info { "Obteniendo de Peliculas.." }

        return repository.findAll()
    }
    override suspend fun findById(id: Int): Result<Peliculas, PeliculasError> {
        logger.info { "Obteniendo Pelicula con id: $id" }
        return repository.findById(id)
            ?.let { Ok(it) }
            ?: Err(PeliculasError.NotFound("No existe la pelicula con id: $id"))
    }

    override suspend fun save(Peliculas: Peliculas): Result<Peliculas, PeliculasError> {
        return repository.save(Peliculas).let { Ok(it) }
    }
}