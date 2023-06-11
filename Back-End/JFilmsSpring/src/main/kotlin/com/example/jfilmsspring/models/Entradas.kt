package com.example.jfilmsspring.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table(name = "entradas")
data class Entradas(
    @Id
    val id :Int?=null,
    @Column("uuid")
    var uuid: String = UUID.randomUUID().toString(),
    @Column("tituloPelicula")
    val tituloPelicula: String,
    @Column("fechaDia")
    val fechaDia: Date,
    @Column("sala")
    val sala: Int,
    @Column("reserva")
    val reservaId: Int,
    @Column("precio")
    val precio: Double,
    @Column("")
    val qr: String =""

) {

}