package com.example.jfilmsspring.validators

import com.example.jfilmsspring.dto.ButacaCreateDto
import com.example.jfilmsspring.errorsResult.ButacasError
import com.github.michaelbull.result.*

fun ButacaCreateDto.validate(): Result<ButacaCreateDto, ButacasError> {
    if (this.fila <1) {
        return Err(ButacasError.BadRequest("La fila no puede ser menor que 1"))
    } else if (this.numero <1) {
        return Err(ButacasError.BadRequest("El numero no puede ser menor que 1"))}
    return Ok(this)
}


