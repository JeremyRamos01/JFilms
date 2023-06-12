package com.example.jfilmsspring.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.util.*

@Table(name = "reservas")
data class Reserva(
    @Id
    val id: Int? = null,
    @Column("fila")
    val fila: Int,
    @Column("uuid")
    var uuid: String = UUID.randomUUID().toString(),
    @Column("butaca")
    val numeroButaca: Int,
    @Column("usuario")
    val usuarioUuid: String,
    @Column("tituloPelicula")
    val tituloPelicula: String,
    @Column("fechaHoraReserva")
    val fechaHoraReserva: LocalDate = LocalDate.now(),


    )
