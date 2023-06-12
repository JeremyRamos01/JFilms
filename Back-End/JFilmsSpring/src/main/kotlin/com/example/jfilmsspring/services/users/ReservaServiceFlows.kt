package com.example.jfilmsspring.services.users

import com.example.jfilmsspring.errorsResult.ReservaError
import com.github.michaelbull.result.Result
import com.example.jfilmsspring.models.Reserva
import kotlinx.coroutines.flow.Flow
import java.util.*

interface ReservaServiceFlows {
    suspend fun findAll(): Flow<Reserva>
    suspend fun findByUuid(uuid: UUID): Result<Reserva, ReservaError>
    suspend fun findById(id: Int): Result<Reserva, ReservaError>
    suspend fun save(Reservas: Reserva): Result<Reserva, ReservaError>
    suspend fun findByUserUuid(uuid: String): Flow<Reserva>
    suspend fun update(uuid: UUID, Reservas: Reserva): Result<Reserva, ReservaError>
    suspend fun deleteById(id: Int): Result<Reserva, ReservaError>
}