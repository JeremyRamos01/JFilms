package com.example.jfilmsspring.dto

import com.example.jfilmsspring.models.Peliculas
import jakarta.validation.constraints.Min
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDate
import java.util.*

data class SalaCreateDto(
   var horaSala: String,
    var pelicula: Int,
)

data class SalaDto(
    var id : Int,
    var uuid: String,
    var horaSala: String,
    var pelicula: Int
)