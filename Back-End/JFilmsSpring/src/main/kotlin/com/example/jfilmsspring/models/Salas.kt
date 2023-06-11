package com.example.jfilmsspring.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table(name = "salas")
data class Salas(
    @Id
    val id :Int?=null,
    @Column("uuid")
    var uuid: String = UUID.randomUUID().toString(),
    @Column("horaSala")
    val horaSala: String,
    @Column("pelicula")
    val pelicula: Int
) {
}