package com.example.jfilmsspring.repositories

import com.example.jfilmsspring.models.Usuario
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository


/**
 * Usuario repository
 *
 * @constructor Create empty Usuario repository
 */

@Repository
interface UsuarioRepository : CoroutineCrudRepository<Usuario, Long> {

    fun findByUuid(uuid: String): Flow<Usuario>

    fun findByName(name: String): Flow<Usuario>
    fun findByEmail(email: String): Flow<Usuario>

    fun findByEmailAndPassword(email:String, password:String):Flow<Usuario>


}