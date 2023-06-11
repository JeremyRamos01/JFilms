package com.example.jfilmsspring.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table(name="peliculas")
data class Peliculas(
    @Id
    val id: Int? = null,
    @Column("title")
    val title: String,
    @Column("overview")
    var overview: String,
    @Column("poster_path")
    var poster_path: String,
    @Column("vote_average")
    var vote_average:  Double,
    )