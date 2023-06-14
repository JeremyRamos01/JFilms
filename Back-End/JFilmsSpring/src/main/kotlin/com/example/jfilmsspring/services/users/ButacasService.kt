package com.example.jfilmsspring.services.users

import com.example.jfilmsspring.config.websockets.ServerWebSocketConfig
import com.example.jfilmsspring.config.websockets.WebSocketHandler
import com.example.jfilmsspring.errorsResult.ButacasError
import com.example.jfilmsspring.mappers.toDto
import com.example.jfilmsspring.models.Butacas
import com.example.jfilmsspring.models.ButacasNotificacion
import com.example.jfilmsspring.models.Notificaciones
import com.example.jfilmsspring.models.Reserva
import com.example.jfilmsspring.repositories.ButacasRepository
import com.example.jfilmsspring.repositories.SalasRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.michaelbull.result.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

private val logger = KotlinLogging.logger {}
@Service
class ButacasService
@Autowired constructor(
    private val repository: ButacasRepository,
    private val salasRepository: SalasRepository,
    private val webSocketConfig: ServerWebSocketConfig
): ButacasServiceFlows {

    private val webSocketService = webSocketConfig.butacasHandler() as WebSocketHandler

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

    override suspend fun findByFilaAndNumero(fila: Int, numero: Int): Result<Butacas, ButacasError> {
            return repository.findByFilaAndNumero(fila, numero).firstOrNull()
                ?.let { Ok(it) }
                ?: Err(ButacasError.NotFound("No existe esta butaca"))
        }




    override suspend fun save(Butacas: Butacas): Result<Butacas, ButacasError> {
        return repository.save(Butacas).let { Ok(it) }
    }

    override suspend fun update(uuid: Int, Butacas: Butacas): Result<Butacas, ButacasError> {
        return findById(uuid).onSuccess { it ->
            val updated = it.copy(
                id = it.id,
                uuid = it.uuid,
                fila = Butacas.fila,
                numero = Butacas.numero,
                reservada = Butacas.reservada,
                tomada = Butacas.tomada
            )
            return repository.save(updated).also {
                 observerChanges(Notificaciones.Tipo.UPDATE, it.uuid) 
            }.let { Ok(it) }.onFailure {
                Err(ButacasError.NotFound("No existe la butaca con id: $uuid"))
            }
        }
    }

    override suspend fun deleteById(id: Int): Result<Butacas, ButacasError> {
        return findById(id).onSuccess {
            Ok(repository.deleteById(id))
        }
    }

    suspend fun observerChanges(noti: Notificaciones.Tipo, id: String, data: Butacas? = null) {
        logger.debug { "Servicio de Butaca con tipo: $noti, id: $id "}
        val mapper = jacksonObjectMapper()
        val json = mapper.writeValueAsString(
             ButacasNotificacion(
                "Butaca",
                noti,
                id,
                data?.toDto(salasRepository.findById(data.salaId)!!)
            )
        )
        val myScope = CoroutineScope(Dispatchers.IO)
        myScope.launch {
            println("HoLA: ${json}")
            webSocketService.sendMessafety(json)
        }
    }




}

