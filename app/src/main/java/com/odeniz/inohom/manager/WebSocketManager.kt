package com.odeniz.inohom.manager

import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
object WebSocketManager {

    private var webSocket: WebSocket? = null
    private val gson = Gson()
    private var messageListener: ((String) -> Unit)? = null
    private const val TAG = "WebSocketManager"
    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    fun connect(ip: String, port: Int, listener: WebSocketListener) {
        if (webSocket == null) {
            val request = Request.Builder()
                .url("ws://$ip:$port")
                .build()
            webSocket = client.newWebSocket(request, listener)
        }
    }

    fun sendMessage(message: Any) {
        val json = gson.toJson(message)
        Log.d(TAG, "sendMessage : $json")
        webSocket?.send(json)
    }

    fun setMessageListener(listener: (String) -> Unit) {
        messageListener = listener
    }

    fun onMessageReceived(message: String) {
        Log.d(TAG, "onMessageReceived : $message")
        messageListener?.invoke(message)
    }

    fun isConnected(): Boolean {
        return webSocket != null
    }

    fun disconnect() {
        webSocket?.close(1000, "Closed by user")
        Log.d(TAG, "disconnect: Closed by user")
        webSocket = null
    }
}
