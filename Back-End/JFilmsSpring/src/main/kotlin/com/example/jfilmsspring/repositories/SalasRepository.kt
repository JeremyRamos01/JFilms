package com.example.jfilmsspring.repositories

import com.example.jfilmsspring.models.Salas
import com.example.jfilmsspring.models.Usuario
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID


/**
 * Usuario repository
 *
 * @constructor Create empty Usuario repository
 */

@Repository
interface SalasRepository : CoroutineCrudRepository<Salas, Int> {

    fun findByUuid(uuid: UUID): Flow<Salas>
    fun findByPelicula(pelicula: Int): Flow<Salas>
}