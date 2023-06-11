package com.example.jfilmsspring.services.users

import com.github.michaelbull.result.Result
import com.example.jfilmsspring.errorsResult.PeliculasError
import com.example.jfilmsspring.models.Peliculas
import kotlinx.coroutines.flow.Flow
import java.util.*

interface PeliculasServiceFlows {
    suspend fun findAll(): Flow<Peliculas>
    suspend fun findById(id: Int): Result<Peliculas, PeliculasError>
    suspend fun save(Peliculas: Peliculas): Result<Peliculas, PeliculasError>
}