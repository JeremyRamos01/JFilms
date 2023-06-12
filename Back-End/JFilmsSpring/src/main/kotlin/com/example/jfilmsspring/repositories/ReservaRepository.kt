package com.example.jfilmsspring.repositories

import com.example.jfilmsspring.models.Reserva
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
interface ReservaRepository : CoroutineCrudRepository<Reserva, Int> {

    fun findByUuid(uuid: UUID): Flow<Reserva>
    fun findByUsuarioUuid(uuid: String): Flow<Reserva>

}