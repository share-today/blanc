package com.blanc.data.model.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("result")
    val result: Boolean,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: T
)