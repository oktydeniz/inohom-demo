package com.odeniz.inohom.usecase

import android.util.Log
import com.odeniz.inohom.model.AuthenticateResponse
import com.odeniz.inohom.repository.AuthRepository
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(private val authRepository: AuthRepository) {
    private val TAG = "AuthenticateUserUseCase"
    fun execute(username: String, password: String, callback: (AuthenticateResponse) -> Unit) {
        Log.d(TAG, "execute Login Request : $username")
        authRepository.authenticate(username, password, callback)
    }
}