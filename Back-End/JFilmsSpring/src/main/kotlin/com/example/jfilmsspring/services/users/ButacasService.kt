package com.example.jfilmsspring.services.users

import com.example.jfilmsspring.errorsResult.ButacasError
import com.example.jfilmsspring.models.Butacas
import com.example.jfilmsspring.repositories.ButacasRepository
import com.github.michaelbull.result.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

private val logger = KotlinLogging.logger {}
@Service
class ButacasService
@Autowired constructor(
    private val repository: ButacasRepository,
): ButacasServiceFlows {


    override suspend fun findAll(): Flow<Butacas> {
        logger.info { "Obteniendo de Butacas.." }

        return repository.findAll()
    }

    override suspend fun findByUuid(uuid: UUID): Result<Butacas, ButacasError> {
        logger.info { "Obteniendo de Butaca con Uuid: $uuid" }
        return repository.findByUuid(uuid).firstOrNull()
            ?.let { Ok(it) }
            ?: Err(ButacasError.NotFound("No existe la butaca  con Uuid: $uuid"))
    }

    override suspend fun findById(id: Int?): Result<Butacas, ButacasError> {
        logger.info { "Obteniendo de Butaca con id: $id" }
        return repository.findById(id!!)
            ?.let { Ok(it) }
            ?: Err(ButacasError.NotFound("No existe la butaca  con id: $id"))
    }



    override suspend fun findBySalaId(salaId: Int): Flow<Butacas> {
        return  repository.findBySalaId(salaId)
    }

    override suspend fun save(Butacas: Butacas): Result<Butacas, ButacasError> {
        return repository.save(Butacas).let { Ok(it) }
    }

    override suspend fun update(uuid: Int, Butacas: Butacas): Result<Butacas, ButacasError> {
        return findById(uuid).onSuccess {
            val updated = it.copy(
                id = it.id,
                uuid = it.uuid,
                fila = Butacas.fila,
                numero = Butacas.numero,
                reservada = Butacas.reservada,
                tomada =  Butacas.tomada
            )
            return Ok(repository.save(updated))
        }.onFailure {
            Err(ButacasError.NotFound("No existe la butaca con id: $uuid"))
        }
    }

    override suspend fun deleteById(id: Int): Result<Butacas, ButacasError> {
        return findById(id).onSuccess {
            Ok(repository.deleteById(id))
        }
    }




}

