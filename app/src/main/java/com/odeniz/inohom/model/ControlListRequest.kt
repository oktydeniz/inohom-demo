package com.odeniz.inohom.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ControlListRequest(
    @SerializedName("is_request")
    val isRequest: Boolean = true,
    val id: Int = 5,
    val params: List<Any> = listOf(mapOf<String, Any>()),
    val method: String = "GetControlList"
) : Serializable


data class ControlListResponse(
    val id: Int,
    val params: List<ControlListData>,
    val method: String,
    val error: Any?,
    @SerializedName("is_request")
    val isRequest: Boolean = true
)

data class ControlListData(
    val data: List<Control>
)

data class Control(
    val id: String,
    val name: String,
    @SerializedName("type_id")
    val typeId: String,
    @SerializedName("bridge_device_id")
    val bridgeDeviceId: String,
    @SerializedName("current_value")
    val currentValue: Int,
    val slot: Int,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("temperature_settings")
    val temperatureSettings: TemperatureSettings?,
    @SerializedName("area_id")
    val areaId: String,
    val parameters: ControlParameters
)

data class TemperatureSettings(
    @SerializedName("has_heating")
    val hasHeating: Boolean,

    @SerializedName("has_cooling")
    val hasCooling: Boolean,

    @SerializedName("bridge_device_id")
    val bridgeDeviceId: String,

    @SerializedName("virtual_control_id")
    val virtualControlId: String?,

    @SerializedName("input_id")
    val inputId: String,

    @SerializedName("is_mode_heating")
    val isModeHeating: Boolean,
    val whole: Int,
    val fraction: Int
)

data class ControlParameters(
    @SerializedName("default_value")
    val defaultValue: Int,

    @SerializedName("output_number")
    val outputNumber: Int,

    @SerializedName("should_output_reverse")
    val shouldOutputReverse: Boolean,

    @SerializedName("should_remember_last_value")
    val shouldRememberLastValue: Boolean,

    @SerializedName("end_time")
    val endTime: String?,

    @SerializedName("start_time")
    val startTime: String?,

    @SerializedName("is_notification")
    val isNotification: Boolean
)


data class UpdateControlValueRequest(
    @SerializedName("is_request")
    val isRequest: Boolean = true,
    val id: Int = 84,
    val params: List<UpdateControlParams>,
    val method: String = "UpdateControlValue"
)

data class UpdateControlParams(
    val id: String,
    val value: Int
)

data class UpdateControlValueResponse(
    val id: Int,
    val params: List<EntityUpdate>,
    val method: String,
    val error: Any?,
    @SerializedName("is_request")
    val isRequest: Boolean = true
)

data class EntityUpdate(
    val entity: Control,
    val type: String
)