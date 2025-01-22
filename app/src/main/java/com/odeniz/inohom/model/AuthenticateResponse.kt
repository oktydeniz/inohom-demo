package com.odeniz.inohom.model

import com.google.gson.annotations.SerializedName

data class AuthenticateResponse(
    val id: Int,
    val params: List<String>?,
    val method: String,
    val error: String?,
    @SerializedName("is_request")
    val isRequest: Boolean,
)