package com.example.jfilmsspring.dto

import jakarta.validation.constraints.Min

data class ButacaCreateDto(
    @Min(value = 1, message = "La fila no puede ser negativo ni ser menor que 0")
    val fila: Int,
    @Min(value = 1, message = "El numero de la butaca no puede ser negativo ni ser menor que 0")
    val numero: Int,
    @Min(value = 1, message = "El numero de la sala no puede ser negativo ni ser menor que 0")
    val salaId: Int,
    val reservada: Boolean?,
    val tomada: Boolean?
)

data class ButacaDto(
    val fila: Int,
    val id : Int,
    val numero: Int,
    val salaId: Int,
    val reservada: Boolean,
    val tomada: Boolean


)