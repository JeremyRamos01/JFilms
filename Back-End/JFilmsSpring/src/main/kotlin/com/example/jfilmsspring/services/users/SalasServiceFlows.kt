package com.example.jfilmsspring.services.users

import com.github.michaelbull.result.Result
import com.example.jfilmsspring.errorsResult.SalasError
import com.example.jfilmsspring.models.Salas
import kotlinx.coroutines.flow.Flow
import java.util.*

interface SalasServiceFlows {
    suspend fun findAll(): Flow<Salas>
    suspend fun findByPeliculaId(peliculaId: Int): Flow<Salas>
    suspend fun findByUuid(uuid: UUID): Result<Salas, SalasError>
    suspend fun findById(id: Int): Result<Salas, SalasError>
    suspend fun save(Salas: Salas): Result<Salas, SalasError>
    suspend fun update(uuid: UUID, Salas: Salas): Result<Salas, SalasError>
    suspend fun deleteById(id: Int): Result<Salas, SalasError>
}