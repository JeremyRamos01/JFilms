package com.example.jfilmsspring.errorsResult

sealed class EntradasError(val message: String) {
    class NotFound(message: String) : EntradasError(message)
    class BadRequest(message: String) : EntradasError(message)
}