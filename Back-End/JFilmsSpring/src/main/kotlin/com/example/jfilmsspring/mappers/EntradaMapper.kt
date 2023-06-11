package com.example.jfilmsspring.mappers

import com.example.jfilmsspring.dto.EntradaCreateDto
import com.example.jfilmsspring.dto.EntradaDto
import com.example.jfilmsspring.models.Entradas
import com.example.jfilmsspring.models.Reserva

fun Entradas.toDto(reserva: Reserva) = EntradaDto(
    uuid = this.uuid,
    tituloPelicula = this.tituloPelicula,
    fechaDia = this.fechaDia,
    sala = this.sala,
    reservaId = reserva.id!!,
    precio = this.precio,
    qr = this.qr
)

fun EntradaCreateDto.toModel() = Entradas(
 tituloPelicula = this.tituloPelicula,
    fechaDia = this.fechaDia,
    sala = this.sala,
    reservaId = this.reservaId,
    precio = this.precio,
    qr = this.qr
)

