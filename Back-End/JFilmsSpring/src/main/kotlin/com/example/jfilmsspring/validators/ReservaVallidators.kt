package com.example.jfilmsspring.validators

import com.example.jfilmsspring.dto.ReservaCreateDto
import com.example.jfilmsspring.errorsResult.ReservaError
import com.github.michaelbull.result.*

fun ReservaCreateDto.validate(): Result<ReservaCreateDto,ReservaError> {
    if (this.fila <1) {
        return Err(ReservaError.BadRequest("La fila no puede ser menor que 1"))
    } else if (this.numeroButaca <=1) {
        return Err(ReservaError.BadRequest("La butaca no puede ser menor que 1"))
    }
    return Ok(this)
}