package com.example.jfilmsspring.repositories

import com.example.jfilmsspring.models.Peliculas
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
interface PeliculasRepository : CoroutineCrudRepository<Peliculas, Int> {

    fun findByid(id: Int): Flow<Peliculas>

}