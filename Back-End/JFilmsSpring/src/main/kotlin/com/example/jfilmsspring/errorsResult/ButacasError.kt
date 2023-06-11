package com.example.jfilmsspring.errorsResult

sealed class ButacasError(val message: String) {
    class NotFound(message: String) : ButacasError(message)
    class BadRequest(message: String) : ButacasError(message)
}

