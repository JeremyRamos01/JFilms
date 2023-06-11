package com.example.jfilmsspring.config.websockets

import mu.KotlinLogging
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.SubProtocolCapable
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.CopyOnWriteArraySet

private val logger = KotlinLogging.logger {}

class WebSocketHandler(private val entity: String) : TextWebSocketHandler(), SubProtocolCapable, WebSocketSender {

    private val sessions: MutableSet<WebSocketSession> = CopyOnWriteArraySet()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        logger.info { "Bienvenido a JFilms " }
        logger.info { "Sesión: $session" }
        sessions.add(session)
        val message = TextMessage("Que se le ofrece")
        session.sendMessage(message)
    }
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        logger.info { "Conexión cerrada con el servidor: $status" }
        sessions.remove(session)
    }


    override fun getSubProtocols(): MutableList<String> {
        return mutableListOf("subprotocol.demo.websocket")
    }

    override fun sendMessage(message: String) {
        sessions.forEach { session ->
            if (session.isOpen) {
                session.sendMessage(TextMessage(message))
            }
        }
    }

    override fun sendPeriodicMessages() {
        for (session in sessions) {
            if (session.isOpen) {
                val broadcast = "La sesion Termminará dentro de x minutos"
                session.sendMessage(TextMessage(broadcast))
            }

        }
    }
}



