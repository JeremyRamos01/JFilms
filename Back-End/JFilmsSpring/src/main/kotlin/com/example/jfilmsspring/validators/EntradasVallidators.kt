package com.example.jfilmsspring.validators

import com.example.jfilmsspring.dto.ButacaCreateDto
import com.example.jfilmsspring.dto.EntradaCreateDto
import com.example.jfilmsspring.errorsResult.ButacasError
import com.example.jfilmsspring.errorsResult.EntradasError
import com.github.michaelbull.result.*
import java.time.LocalDate
import java.util.*

fun EntradaCreateDto.validate(): Result<EntradaCreateDto, EntradasError> {
    if (this.fechaDia < Date()) {
        return Err(EntradasError.BadRequest("La fecha no puede eser menor a la fecha de hoy"))
    } else if (this.sala <=1) {
        return Err(EntradasError.BadRequest("La sala no puede ser menor que 1"))
    } else if (this.sala <=1) {
     return Err(EntradasError.BadRequest("La sala no puede ser menor que 1"))
    } else if(this.precio <=0){
        return Err(EntradasError.BadRequest("El precio no puede ser menor que 0"))
    }
    return Ok(this)
}