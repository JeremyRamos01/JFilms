package com.example.microserviciousuarios.validators

import com.example.jfilmsspring.dto.UsuarioCreateDto
import com.example.jfilmsspring.dto.UsuarioUpdateDto
import com.example.jfilmsspring.exceptions.UsuarioBadRequestException
/**
 * Método que valida los datos para la creación de los usuarios.
 */
fun UsuarioCreateDto.validate(): UsuarioCreateDto {
    if (this.name.isBlank()) {
        throw UsuarioBadRequestException("El nombre no puede estar vacío")
    } else if (this.email.isBlank() || !this.email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)\$")))
        throw UsuarioBadRequestException("El email no puede estar vacío o no tiene el formato correcto")
    else if (this.password.isBlank() || this.password.length < 5)
        throw UsuarioBadRequestException("El password no puede estar vacío o ser menor de 5 caracteres")
    return this
}

/**
 * Método que valida los datos para la actualización de usuarios.
 */
fun UsuarioUpdateDto.validate(): UsuarioUpdateDto {
     if (this.email.isBlank() || !this.email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)\$")))
        throw UsuarioBadRequestException("El email no puede estar vacío o no tiene el formato correcto")
    else if (this.name.isBlank())
        throw UsuarioBadRequestException("El name no puede estar vacío")
    else if (this.dni.isBlank())
       throw UsuarioBadRequestException("El dni no puede estar vacio o ser menor de 9 caracteres")
    return this
}