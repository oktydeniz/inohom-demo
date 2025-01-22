package com.odeniz.inohom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.odeniz.inohom.model.AuthenticateResponse
import com.odeniz.inohom.usecase.AuthenticateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val authenticateUserUseCase: AuthenticateUserUseCase) : ViewModel() {

    private val _authState = MutableLiveData<AuthenticateResponse>()
    val authState: LiveData<AuthenticateResponse> get() = _authState

    fun authenticate(username: String, password: String) {
        authenticateUserUseCase.execute(username, password) { response ->
            _authState.postValue(response)
        }
    }
}