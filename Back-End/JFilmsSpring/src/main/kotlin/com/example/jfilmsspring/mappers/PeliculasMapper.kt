package com.example.jfilmsspring.mappers

import com.example.jfilmsspring.dto.PeliculasDTO
import com.example.jfilmsspring.dto.UsuarioCreateDto
import com.example.jfilmsspring.dto.UsuarioDto
import com.example.jfilmsspring.models.Peliculas
import com.example.jfilmsspring.models.Usuario

fun Peliculas.toDto(): PeliculasDTO {
    return PeliculasDTO(
        title = this.title,
        id = this.id!!,
        overview = this.overview,
        poster_path = this.poster_path,
        vote_average = this.vote_average
    )
}


fun PeliculasDTO.toModel(): Peliculas {
    return Peliculas(
        title = this.title,
        overview = this.overview,
        poster_path = this.poster_path,
        vote_average = this.vote_average
        )
}