package com.odeniz.inohom.usecase

import android.util.Log
import com.odeniz.inohom.model.ControlListRequest
import com.odeniz.inohom.model.ControlListResponse
import com.odeniz.inohom.model.UpdateControlValueRequest
import com.odeniz.inohom.model.UpdateControlValueResponse
import com.odeniz.inohom.repository.ControlRepository
import javax.inject.Inject

class ControlsUseCase @Inject constructor(private val repository: ControlRepository) {

    private val TAG = "ControlsUseCase"
    fun execute(
        data: ControlListRequest,
        callback: (ControlListResponse) -> Unit,
        callbackError: (Boolean) -> Unit
    ) {
        Log.d(TAG, "execute Data : $data")
        repository.fetchControlLists(data, callback, callbackError)
    }

    fun executeUpdateValue(
        data: UpdateControlValueRequest,
        callback: (UpdateControlValueResponse) -> Unit,
        callbackError: (Boolean) -> Unit
    ) {
        Log.d(TAG, "executeUpdateValue Data : $data")
        repository.executeUpdateValue(data, callback, callbackError)
    }
}