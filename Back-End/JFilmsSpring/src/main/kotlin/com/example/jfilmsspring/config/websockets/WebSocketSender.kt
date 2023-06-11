package com.example.jfilmsspring.config.websockets

interface WebSocketSender {
    fun sendMessage(message: String)
    fun sendPeriodicMessages()
}