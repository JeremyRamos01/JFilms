package com.example.jfilmsspring.mappers

import com.example.jfilmsspring.dto.*
import com.example.jfilmsspring.models.Butacas
import com.example.jfilmsspring.models.Reserva
import com.example.jfilmsspring.models.Salas

fun Salas.toDto() = SalaDto(
    id = this.id!!,
    uuid = this.uuid,
    horaSala = this.horaSala,
    pelicula = this.pelicula,
)
fun SalaCreateDto.toModel() = Salas(

    horaSala = this.horaSala,
    pelicula = this.pelicula,
)