package com.example.jfilmsspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.web.socket.config.annotation.EnableWebSocket

@SpringBootApplication
class JFilmsSpringApplication


fun main(args: Array<String>) {
    runApplication<JFilmsSpringApplication>(*args)
}
