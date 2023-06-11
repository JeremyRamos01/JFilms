package com.example.jfilmsspring.services.users

import com.example.jfilmsspring.errorsResult.ButacasError
import com.example.jfilmsspring.errorsResult.EntradasError
import com.example.jfilmsspring.models.Entradas
import com.example.jfilmsspring.repositories.EntradasRepository
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
class EntradasService
@Autowired constructor(
    private val repository: EntradasRepository,
): EntradasServiceFlows {

    override suspend fun findAll(): Flow<Entradas> {
        logger.info { "Obteniendo de Entradas.." }

        return repository.findAll()
    }

    override suspend fun findByUuid(uuid: UUID): Result<Entradas, EntradasError> {
        logger.info { "Obteniendo de Entrada con Uuid: $uuid" }
        return repository.findByUuid(uuid).firstOrNull()
            ?.let { Ok(it) }
            ?: Err(EntradasError.NotFound("No existe la butaca  con Uuid: $uuid"))
    }

    override suspend fun findById(id: Int): Result<Entradas, EntradasError> {
        logger.info { "Obteniendo de Entrada con id: $id" }
        return repository.findById(id)
            ?.let { Ok(it) }
            ?: Err(EntradasError.NotFound("No existe la butaca  con id: $id"))
    }

    override suspend fun save(Entradas: Entradas): Result<Entradas, EntradasError> {
        return repository.save(Entradas).let { Ok(it) }
    }

    override suspend fun update(uuid: UUID, Entradas: Entradas): Result<Entradas, EntradasError> {
        return findByUuid(uuid).onSuccess {
            Ok(repository.save(Entradas))
        }.onFailure {
            Err(EntradasError.NotFound("No existe la entrada con id: $uuid"))
        }
    }

    override suspend fun deleteById(id: Int): Result<Entradas, EntradasError> {
        return findById(id).onSuccess {
            Ok(repository.deleteById(id))
        }
    }
}