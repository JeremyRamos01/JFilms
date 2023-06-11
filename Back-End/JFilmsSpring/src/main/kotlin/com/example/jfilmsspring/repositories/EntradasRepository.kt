package com.example.jfilmsspring.repositories

import com.example.jfilmsspring.models.Entradas
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID


/**
 * Entradas repository
 *
 * @constructor Create empty Entradas repository
 */

@Repository
interface EntradasRepository : CoroutineCrudRepository<Entradas, Int> {

    fun findByUuid(uuid: UUID): Flow<Entradas>


}