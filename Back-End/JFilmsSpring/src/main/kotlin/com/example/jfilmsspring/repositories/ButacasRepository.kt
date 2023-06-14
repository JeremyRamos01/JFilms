package com.example.jfilmsspring.repositories

import com.example.jfilmsspring.models.Butacas
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID


/**
 * Butacas repository
 *
 * @constructor Create empty Butacas repository
 */

@Repository
interface ButacasRepository : CoroutineCrudRepository<Butacas, Int> {
    fun findByUuid(Uuid: UUID): Flow<Butacas>
    fun findBySalaId(salaId: Int): Flow<Butacas>
    fun findByFilaAndNumero (filaId: Int, numero:Int): Flow<Butacas>
}




