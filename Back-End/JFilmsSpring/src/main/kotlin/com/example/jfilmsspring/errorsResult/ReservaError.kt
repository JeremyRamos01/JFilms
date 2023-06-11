package com.example.jfilmsspring.errorsResult

sealed class ReservaError(val message: String) {
    class NotFound(message: String) : ReservaError(message)
    class BadRequest(message: String) : ReservaError(message)
}