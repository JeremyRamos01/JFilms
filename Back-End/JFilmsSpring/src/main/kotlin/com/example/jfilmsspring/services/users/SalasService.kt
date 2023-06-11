package com.example.jfilmsspring.services.users

import com.example.jfilmsspring.errorsResult.ButacasError
import com.example.jfilmsspring.errorsResult.SalasError
import com.example.jfilmsspring.models.Salas
import com.example.jfilmsspring.repositories.SalasRepository
import com.github.michaelbull.result.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.onSuccess

private val logger = KotlinLogging.logger {}
@Service
class SalasService
@Autowired constructor(
    private val repository: SalasRepository,
): SalasServiceFlows {

    override suspend fun findAll(): Flow<Salas> {
        logger.info { "Obteniendo de Salas.." }

        return repository.findAll()
    }

    override suspend fun findByPeliculaId(peliculaId: Int): Flow<Salas> {
        return  repository.findByPelicula(peliculaId)
    }

    override suspend fun findByUuid(uuid: UUID): Result<Salas, SalasError> {
        logger.info { "Obteniendo de Sala con Uuid: $uuid" }
        return repository.findByUuid(uuid).firstOrNull()
            ?.let { Ok(it) }
            ?: Err(SalasError.NotFound("No existe la butaca  con Uuid: $uuid"))
    }

    override suspend fun findById(id: Int): Result<Salas, SalasError> {
        logger.info { "Obteniendo de Sala con id: $id" }
        return repository.findById(id)
            ?.let { Ok(it) }
            ?: Err(SalasError.NotFound("No existe la butaca  con id: $id"))
    }

    override suspend fun save(Salas: Salas): Result<Salas, SalasError> {
        return repository.save(Salas).let { Ok(it) }
    }

    override suspend fun update(uuid: UUID, Salas: Salas): Result<Salas, SalasError> {
        return findByUuid(uuid).onSuccess {
            val updated = it.copy(
                id = it.id,
                uuid = it.uuid,
                horaSala = Salas.horaSala,
                pelicula =Salas.pelicula
            )
            return Ok(repository.save(updated))
        }.onFailure {
        Err(ButacasError.NotFound("No existe la sala con id: $uuid"))
    }
    }

    override suspend fun deleteById(id: Int): Result<Salas, SalasError> {
        return findById(id).onSuccess {
            Ok(repository.deleteById(id))
        }
    }
}