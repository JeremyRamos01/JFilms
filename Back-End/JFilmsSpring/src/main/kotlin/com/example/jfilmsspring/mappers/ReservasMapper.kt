package com.example.jfilmsspring.mappers

import com.example.jfilmsspring.dto.ReservaCreateDto
import com.example.jfilmsspring.dto.ReservaDto
import com.example.jfilmsspring.models.Reserva
import java.time.LocalDate

fun Reserva.toDto() = ReservaDto(
   fila = this.fila,
    uuid = this.uuid,
    numeroButaca = this.numeroButaca,
    fechaHoraReserva = this.fechaHoraReserva ,
    usuarioUuid = this.usuarioUuid,
    tituloPelicula = this.tituloPelicula
)
fun ReservaCreateDto.toModel() = Reserva(
    fila = this.fila,
    numeroButaca = this.numeroButaca,
    fechaHoraReserva = LocalDate.now(),
    usuarioUuid = this.usuarioUuid,
    tituloPelicula = this.tituloPelicula
)

fun ReservaDto.toModel() = Reserva(
    fila = this.fila,
    numeroButaca = this.numeroButaca,
    fechaHoraReserva = this.fechaHoraReserva,
    usuarioUuid = this.usuarioUuid,
    tituloPelicula = this.tituloPelicula

)

