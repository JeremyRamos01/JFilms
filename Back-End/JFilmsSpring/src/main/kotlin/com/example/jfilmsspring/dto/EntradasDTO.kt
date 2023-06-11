package com.example.jfilmsspring.dto

import com.example.jfilmsspring.models.Reserva
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import java.util.*

data class EntradaCreateDto(
    @NotEmpty(message = "Titulo no puede estar vacio")
    val tituloPelicula: String,
    val fechaDia: Date,
    @Min(value = 1, message = "La sala no puede ser negativo ni ser menor que 0")
    val sala: Int,
    val reservaId: Int,
    @Min(value = 1, message = "El precio no puede ser negativo ni ser menor que 0")
    val precio: Double,
    val qr: String
)

data class EntradaDto(
    val uuid: String,
    val tituloPelicula: String,
    val fechaDia: Date,
    val sala: Int,
    val reservaId: Int,
    val precio: Double,
    val qr: String

)