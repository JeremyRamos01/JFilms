package com.example.jfilmsspring.dto
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import org.springframework.data.relational.core.mapping.Column

data class PeliculasDTO(
    val title: String,
    val id: Int,
    var overview: String,
    var poster_path: String,
    var vote_average:  Double,
)

@Serializable
data class EsqueletoMovieDB (
    val dates: Dates,
    val page: Long,
    val results: List<PeliculaObtenida>,

    @SerialName("total_pages")
    val totalPages: Long,

    @SerialName("total_results")
    val totalResults: Long
)

@Serializable
data class Dates (
    val maximum: String,
    val minimum: String
)

@Serializable
data class PeliculaObtenida (
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String,

    @SerialName("genre_ids")
    val genreIDS: List<Long>,

    val id: Long,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("release_date")
    val releaseDate: String,

    val title: String,
    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Long
)
