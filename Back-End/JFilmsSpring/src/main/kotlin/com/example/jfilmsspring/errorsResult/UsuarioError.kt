package com.example.jfilmsspring.errorsResult

sealed class UsuarioError(val message: String) {
    class NotFound(message: String) : UsuarioError(message)
    class BadRequest(message: String) : UsuarioError(message)
    class Unauthorized(message: String) : UsuarioError(message)
}