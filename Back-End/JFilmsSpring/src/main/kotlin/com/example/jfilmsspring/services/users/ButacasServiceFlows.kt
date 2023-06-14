package com.example.jfilmsspring.services.users

import com.github.michaelbull.result.Result
import com.example.jfilmsspring.errorsResult.ButacasError
import com.example.jfilmsspring.models.Butacas
import kotlinx.coroutines.flow.Flow
import java.util.*

interface ButacasServiceFlows {
    suspend fun findAll(): Flow<Butacas>
    suspend fun findByUuid(uuid: UUID): Result<Butacas,ButacasError>
    suspend fun findById(id: Int?): Result<Butacas, ButacasError>
    suspend fun  findBySalaId(salaId: Int): Flow<Butacas>
    suspend fun findByFilaAndNumero(fila: Int, numero: Int):  Result<Butacas,ButacasError>
    suspend fun save(Butacas: Butacas): Result<Butacas, ButacasError>
    suspend fun update(uuid: Int, Butacas: Butacas): Result<Butacas, ButacasError>
    suspend fun deleteById(id: Int): Result<Butacas, ButacasError>
}


