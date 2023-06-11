package com.example.jfilmsspring.mappers

import com.example.jfilmsspring.dto.UsuarioCreateDto
import com.example.jfilmsspring.dto.UsuarioDto
import com.example.jfilmsspring.models.Usuario


fun Usuario.toDto(): UsuarioDto {

    return UsuarioDto(

        uuid = this.uuid,
        email = this.email,
        name = this.name,
        tarjeta = this.tarjeta,
        monto = this.monto,
        dni=this.dni,
        rol =  this.rol.split(",").map { it.trim() }.toSet(),
    )

}


fun UsuarioCreateDto.toModel(): Usuario {
    return Usuario(
        email = this.email,
        name = this.name,
        tarjeta = this.tarjeta,
        monto = 300.0,
        dni=this.dni,
        password = this.password,

    )
}