package com.odeniz.inohom.repository

import com.google.gson.Gson
import com.odeniz.inohom.manager.WebSocketManager
import com.odeniz.inohom.model.ControlListRequest
import com.odeniz.inohom.model.ControlListResponse
import com.odeniz.inohom.model.UpdateControlValueRequest
import com.odeniz.inohom.model.UpdateControlValueResponse
import javax.inject.Inject

class ControlRepository @Inject constructor(private val webSocketManager: WebSocketManager) {

    fun fetchControlLists(
        data: ControlListRequest,
        callback: (ControlListResponse) -> Unit,
        callbackError: (Boolean) -> Unit
    ) {
        webSocketManager.sendMessage(data)

        webSocketManager.setMessageListener { message ->
            val response = Gson().fromJson(message, ControlListResponse::class.java)
            if (response.method == "GetControlList") {
                callback(response)
            } else {
                callbackError(true)
            }
        }
    }

    fun executeUpdateValue(
        data: UpdateControlValueRequest,
        callback: (UpdateControlValueResponse) -> Unit,
        callbackError: (Boolean) -> Unit
    ) {
        webSocketManager.sendMessage(data)

        webSocketManager.setMessageListener { message ->
            val response = Gson().fromJson(message, UpdateControlValueResponse::class.java)
            if (response.method == "OnEntityUpdated") {
                callback(response)
            } else {
                callbackError(true)
            }
        }
    }
}