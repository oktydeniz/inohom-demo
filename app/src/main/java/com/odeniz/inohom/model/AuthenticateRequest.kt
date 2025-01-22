package com.odeniz.inohom.model

import com.google.gson.annotations.SerializedName

data class AuthenticateRequest(
    @SerializedName("is_request")
    val isRequest: Boolean,
    val id: Int,
    val params: List<AuthParams>,
    val method: String
)

data class AuthParams(
    val username: String,
    val password: String
)