package com.example.jfilmsspring.dto

import com.example.jfilmsspring.models.Usuario

data class UsuarioDto(
    val uuid:String,
    val email: String,
    val name: String,
    val dni: String,
    val tarjeta: String,
    val monto: Double,
    val rol: Set<String> = setOf(Usuario.TypeRol.USER.name),
)

data class UsuarioCreateDto(
    val email: String,
    val name: String,
    val password: String,
    val dni:String,
    val tarjeta: String,
    val monto: Double?,
    val rol:Set<String>? = setOf(Usuario.TypeRol.USER.name),
)

data class UsuarioUpdateDto(
    val email:String,
    val name:String,
    val dni:String,
    val monto:Double?,
    val tarjeta: String
)

data class UsuarioWithTokenDto(
    val user: UsuarioDto,
    val token: String
)

data class UsuarioLoginDto(
    val name: String,
    val password: String
)



