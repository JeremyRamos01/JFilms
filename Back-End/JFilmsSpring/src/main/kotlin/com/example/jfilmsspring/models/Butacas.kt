package com.example.jfilmsspring.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table(name = "butacas")
data class Butacas(
    @Id
    val id :Int?=null,
    @Column("uuid")
     var uuid: String = UUID.randomUUID().toString(),
    @Column("fila")
    val fila:Int,
    @Column("numero")
    val numero: Int,
    @Column("salaid")
    val salaId: Int,
    @Column("reservada")
    val reservada: Boolean,
    @Column("tomada")
    val tomada: Boolean

) {
}