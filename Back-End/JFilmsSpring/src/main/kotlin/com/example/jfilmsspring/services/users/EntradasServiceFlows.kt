package com.example.jfilmsspring.services.users

import com.github.michaelbull.result.Result
import com.example.jfilmsspring.errorsResult.EntradasError
import com.example.jfilmsspring.models.Entradas
import kotlinx.coroutines.flow.Flow
import java.util.*

interface EntradasServiceFlows {
    suspend fun findAll(): Flow<Entradas>
    suspend fun findByUuid(uuid: UUID): Result<Entradas, EntradasError>
    suspend fun findById(id: Int): Result<Entradas, EntradasError>
    suspend fun save(Entradas: Entradas): Result<Entradas, EntradasError>
    suspend fun update(uuid: UUID, Entradas: Entradas): Result<Entradas, EntradasError>
    suspend fun deleteById(id: Int): Result<Entradas, EntradasError>
}