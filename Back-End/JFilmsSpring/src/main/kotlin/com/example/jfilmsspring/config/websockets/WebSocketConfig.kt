package com.example.jfilmsspring.config.websockets

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class ServerWebSocketConfig : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(reservasHandler(), "api/updates/reserva")
            .setAllowedOrigins("http://localhost:4200")
        registry.addHandler(butacasHandler(), "api/updates/butacas")
            .setAllowedOrigins("http://localhost:4200")

    }

    @Bean
    fun reservasHandler(): WebSocketHandler {
        return WebSocketHandler("Reservas")
    }

    @Bean
    fun butacasHandler(): WebSocketHandler {
        return WebSocketHandler("Butacas")

    }

}