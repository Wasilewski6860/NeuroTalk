package com.example.data.storage.network.web_socket

import kotlinx.coroutines.flow.Flow

interface ChatWebSocket {
    fun connect()
    fun sendMessage(message: String)
    fun observeMessages(): Flow<String>
    fun disconnect()
}