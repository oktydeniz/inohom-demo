package com.odeniz.inohom.repository

import com.google.gson.Gson
import com.odeniz.inohom.manager.WebSocketManager
import com.odeniz.inohom.model.AuthParams
import com.odeniz.inohom.model.AuthenticateRequest
import com.odeniz.inohom.model.AuthenticateResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(private val webSocketManager: WebSocketManager) {

    fun authenticate(username: String, password: String, callback: (AuthenticateResponse) -> Unit) {
        val request = AuthenticateRequest(
            isRequest = true,
            id = 8,
            params = listOf(AuthParams(username, password)),
            method = "Authenticate"
        )
        webSocketManager.sendMessage(request)

        webSocketManager.setMessageListener { message ->
            val response = Gson().fromJson(message, AuthenticateResponse::class.java)
            if (response.method == "OnAuthenticated") {
                callback(response)
            }
        }
    }
}
