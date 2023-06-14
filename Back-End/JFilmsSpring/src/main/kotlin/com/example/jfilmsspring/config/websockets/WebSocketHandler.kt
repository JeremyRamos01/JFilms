package com.example.jfilmsspring.config.websockets

import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.SubProtocolCapable
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import org.springframework.web.util.HtmlUtils
import java.io.IOException
import java.util.concurrent.CopyOnWriteArraySet

private val logger = KotlinLogging.logger {}

class WebSocketHandler(private val entity: String) : TextWebSocketHandler(), SubProtocolCapable, WebSocketSender {

    private val sessions: MutableSet<WebSocketSession> = CopyOnWriteArraySet()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        logger.info { "Bienvenido a JFilms " }
        logger.info { "Sesión: $session"  }
        sessions.add(session)

        val message = TextMessage("Que se le ofrece")
        session.sendMessage(message)

    }
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        logger.info { "Conexión cerrada con el servidor: $status" }
        var x = this.sessions.size
        sessions.remove(session)
    }

    fun sendMessafety(message: String){
        var x = this.sessions.size
        sessions.forEach { session ->
            if (session.isOpen) {
                session.sendMessage(TextMessage(message))
            }
        }
    }

    @Throws(Exception::class)
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        // No hago nada con los mensajes que me llegan
        val x = sessions.size
        val request = message.payload
        logger.info("Server received: {}", request)
        val response = String.format("response from server to '%s'", HtmlUtils.htmlEscape(request))
        logger.info("Server sends: {}", response)
        session.sendMessage(TextMessage(response))
    }


    override fun getSubProtocols(): MutableList<String> {
        return mutableListOf("subprotocol.demo.websocket")
    }

    override fun sendMessage(message: String) {
        var x = this.sessions.size
        sessions.forEach { session ->
            if (session.isOpen) {
                session.sendMessage(TextMessage(message))
            }
        }
    }
    @Scheduled(fixedRate = 10)
    @Throws(IOException::class)
    override fun sendPeriodicMessages() {
        for (session in sessions) {
            if (session.isOpen) {
                val broadcast = "La sesion Termminará dentro de x minutos"
                session.sendMessage(TextMessage(broadcast))
            }

        }
    }
}



