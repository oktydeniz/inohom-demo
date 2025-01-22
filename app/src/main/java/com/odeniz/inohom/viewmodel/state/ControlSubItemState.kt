package com.odeniz.inohom.viewmodel.state

import com.odeniz.inohom.model.ControlListResponse
import com.odeniz.inohom.model.UpdateControlValueResponse

sealed class ControlSubItemState {
    data object Loading : ControlSubItemState()
    data class Response(val response: ControlListResponse) : ControlSubItemState()
    data class ResponseClicked(val response: UpdateControlValueResponse) : ControlSubItemState()
    data class Error(val message: String) : ControlSubItemState()
}