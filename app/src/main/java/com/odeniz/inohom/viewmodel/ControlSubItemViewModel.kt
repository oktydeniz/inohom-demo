package com.odeniz.inohom.viewmodel

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.odeniz.inohom.R
import com.odeniz.inohom.model.ControlListRequest
import com.odeniz.inohom.model.UpdateControlParams
import com.odeniz.inohom.model.UpdateControlValueRequest
import com.odeniz.inohom.usecase.ControlsUseCase
import com.odeniz.inohom.viewmodel.state.ControlSubItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ControlSubItemViewModel @Inject constructor(
    private val useCase: ControlsUseCase,
    private val resources: Resources,
) : ViewModel() {

    private val _state = MutableLiveData<ControlSubItemState>()
    val state: LiveData<ControlSubItemState> get() = _state
    private val TAG = "ControlSubItemViewModel"

    fun fetchControls(control: ControlListRequest) {
        _state.value = ControlSubItemState.Loading
        useCase.execute(control, callbackError = {
            _state.value =
                ControlSubItemState.Error(message = resources.getString(R.string.something_went_wrong))
            Log.e(TAG, "Error : fetchControls ")
        }, callback = { response ->
            _state.value = ControlSubItemState.Response(response)
        })
    }

    fun updateControlValue(id: String, value: Int) {
        val request = UpdateControlValueRequest(params = listOf(UpdateControlParams(id, value)))
        useCase.executeUpdateValue(request, callbackError = {
            _state.value =
                ControlSubItemState.Error(message = resources.getString(R.string.something_went_wrong))
            Log.e(TAG, "Error : updateControlValue ")
        }, callback = { response ->
            _state.value = ControlSubItemState.ResponseClicked(response)
        })
    }
}