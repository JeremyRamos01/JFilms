package com.example.jfilmsspring.validators

import com.example.jfilmsspring.dto.ButacaCreateDto
import com.example.jfilmsspring.dto.SalaCreateDto
import com.example.jfilmsspring.errorsResult.ButacasError
import com.example.jfilmsspring.errorsResult.SalasError
import com.github.michaelbull.result.*

fun SalaCreateDto.validate(): Result<SalaCreateDto, SalasError> {
    if (this.horaSala.isEmpty()) {
        return Err(SalasError.BadRequest("La sala tiene que tener hora"))
    } else if (this.pelicula <=0) {
        return Err(SalasError.BadRequest("La pelicula insertada contiene un id inexistente"))
    }

    return Ok(this)
}