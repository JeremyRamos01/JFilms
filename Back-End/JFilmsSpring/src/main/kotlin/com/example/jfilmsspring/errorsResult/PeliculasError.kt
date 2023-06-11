package com.example.jfilmsspring.errorsResult

sealed class PeliculasError(val message: String) {
    class NotFound(message: String) : PeliculasError(message)
    class BadRequest(message: String) : PeliculasError(message)
}