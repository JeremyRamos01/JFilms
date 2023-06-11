package com.example.jfilmsspring.services.users

import com.example.jfilmsspring.config.websockets.ServerWebSocketConfig
import com.example.jfilmsspring.config.websockets.WebSocketHandler
import com.example.jfilmsspring.errorsResult.ReservaError
import com.example.jfilmsspring.mappers.toDto
import com.example.jfilmsspring.models.Butacas
import com.example.jfilmsspring.models.Notificaciones
import com.example.jfilmsspring.models.Reserva
import com.example.jfilmsspring.models.ReservaNotificacion
import com.example.jfilmsspring.repositories.ReservaRepository
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
import java.time.LocalDate
import java.util.*

private val logger = KotlinLogging.logger {}
@Service
class ReservaService
@Autowired constructor(
    private val repository: ReservaRepository,
    private val webSocketConfig: ServerWebSocketConfig
): ReservaServiceFlows {

    private val webSocketService = webSocketConfig.reservasHandler() as WebSocketHandler

    override suspend fun findAll(): Flow<Reserva> {
        logger.info { "Obteniendo de Reserva.." }
        return repository.findAll()
    }

    override suspend fun findByUuid(uuid: UUID): Result<Reserva, ReservaError> {
        logger.info { "Obteniendo de Reserva con Uuid: $uuid" }
        return repository.findByUuid(uuid).firstOrNull()
            ?.let { Ok(it) }
            ?: Err(ReservaError.NotFound("No existe la reserva  con Uuid: $uuid"))
    }

    override suspend fun findById(id: Int): Result<Reserva, ReservaError> {
        logger.info { "Obteniendo de Reserva con id: $id" }
        return repository.findById(id)
            ?.let { Ok(it) }
            ?: Err(ReservaError.NotFound("No existe la reserva  con id: $id"))
    }

    override suspend fun save(Reservas: Reserva): Result<Reserva, ReservaError> {
        return repository.save(Reservas)
            .also { observerChanges(Notificaciones.Tipo.CREATE, it.uuid) }.let { Ok(it) }
    }


    override suspend fun update(uuid: UUID, Reservas: Reserva): Result<Reserva, ReservaError> {
        return findByUuid(uuid).onSuccess {
            Ok(repository.save(Reservas))
        }.onFailure {
            Err(ReservaError.NotFound("No existe la reserva con id: $uuid"))
        }
    }



    override suspend fun deleteById(id: Int): Result<Reserva, ReservaError> {
        return findById(id).onSuccess {
            Ok(repository.deleteById(id)).also {
                observerChanges(Notificaciones.Tipo.DELETE, it.value.toString())
            }
        }
    }

    suspend fun observerChanges(noti: Notificaciones.Tipo, id: String, data: Reserva? = null) {
        logger.debug { "Servicio de Reserva con tipo: $noti, id: $id "}
        val mapper = jacksonObjectMapper()
        val json = mapper.writeValueAsString(
            ReservaNotificacion(
                "Reserva",
                noti,
                id,
                data?.toDto()
            )
        )
        val myScope = CoroutineScope(Dispatchers.IO)
        myScope.launch {
            webSocketService.sendMessage(json)
        }
    }



}