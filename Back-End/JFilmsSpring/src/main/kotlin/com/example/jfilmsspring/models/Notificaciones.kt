package com.example.jfilmsspring.models
import com.example.jfilmsspring.dto.ReservaDto
import java.time.LocalDateTime


data class Notificaciones<T>(
    val entity: String,
    val type: Tipo,
    val id: String,
    val data: T,
    val createdAt: String = LocalDateTime.now().toString()
) {
    enum class Tipo { CREATE,  DELETE }
}

typealias ReservaNotificacion = Notificaciones<ReservaDto?>

