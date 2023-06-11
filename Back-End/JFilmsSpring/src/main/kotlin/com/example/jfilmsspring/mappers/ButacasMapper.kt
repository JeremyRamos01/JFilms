package com.example.jfilmsspring.mappers

import com.example.jfilmsspring.dto.ButacaCreateDto
import com.example.jfilmsspring.dto.ButacaDto
import com.example.jfilmsspring.models.Butacas
import com.example.jfilmsspring.models.Salas

fun Butacas.toDto(sala: Salas) = ButacaDto(
    salaId = sala.id!!,
    id = this.id!!,
    fila = this.fila,
    numero = this.numero,
    reservada = this.reservada,
    tomada = this.tomada
)
fun ButacaCreateDto.toModel() = Butacas(
    fila = this.fila,
    numero = this.numero,
    salaId = this.salaId,
    reservada = this.reservada!!,
    tomada = this.tomada!!

)