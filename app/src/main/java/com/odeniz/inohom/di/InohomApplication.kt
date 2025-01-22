package com.odeniz.inohom.di

import android.app.Application
import android.util.Log
import com.odeniz.inohom.manager.WebSocketManager
import dagger.hilt.android.HiltAndroidApp
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject


@HiltAndroidApp
class InohomApplication : Application() {

    @Inject
    lateinit var webSocketManager: WebSocketManager

    override fun onCreate() {
        super.onCreate()

        webSocketManager.connect(
            ip = "85.105.107.53",
            port = 9095,
            listener = object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    super.onOpen(webSocket, response)
                    Log.d("WebSocket", "Connected to WebSocket server")
                }

                override fun onMessage(webSocket: WebSocket, text: String) {
                    super.onMessage(webSocket, text)
                    Log.d("WebSocket", "Message received: $text")
                    WebSocketManager.onMessageReceived(text)
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    super.onFailure(webSocket, t, response)
                    Log.e("WebSocket", "Connection failed", t)
                }
            }
        )
    }

    override fun onTerminate() {
        super.onTerminate()
        webSocketManager.disconnect()
    }

}