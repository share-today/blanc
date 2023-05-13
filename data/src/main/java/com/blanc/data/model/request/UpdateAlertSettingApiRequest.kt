package com.blanc.data.model.request

import com.google.gson.annotations.SerializedName

data class UpdateAlertSettingApiRequest(
    @SerializedName("alert")
    val isAlertEnable: Boolean
)