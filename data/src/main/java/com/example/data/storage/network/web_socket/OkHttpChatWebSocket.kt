package com.example.data.storage.network.web_socket

import com.example.data.storage.network.Endpoints.CHAT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import org.java_websocket.client.WebSocketClient


class OkHttpChatWebSocket(private val client: OkHttpClient) : ChatWebSocket {

    private var webSocket: WebSocket? = null
    private val _messageFlow = MutableSharedFlow<String>()
    private val messageFlow = _messageFlow.asSharedFlow()

    override fun connect() {

        val request = Request.Builder().url(CHAT).build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                // WebSocket is opened
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                // Receive a message
                _messageFlow.tryEmit(text)
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                // Handle binary messages if necessary
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                webSocket.close(code, reason)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                // Handle errors
                t.printStackTrace()
            }
        })

        client.dispatcher.executorService.shutdown()
    }

    override fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    override fun observeMessages(): Flow<String> = messageFlow

    override fun disconnect() {
        webSocket?.close(1000, "Goodbye")
    }
}
