package com.example.jfilmsspring.dto

import java.time.LocalDate
import java.util.*

data class ReservaCreateDto(
    val fila: Int,
    val numeroButaca: Int,
    val fechaHoraReserva: LocalDate?
)

data class ReservaDto(
    val fila: Int,
    var uuid: String,
    val numeroButaca: Int,
    val fechaHoraReserva: LocalDate

)