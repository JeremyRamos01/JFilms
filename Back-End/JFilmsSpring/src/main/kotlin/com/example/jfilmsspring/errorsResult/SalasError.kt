package com.example.jfilmsspring.errorsResult

sealed class SalasError(val message: String) {
    class NotFound(message: String) : SalasError(message)
    class BadRequest(message: String) : SalasError(message)
}