package com.blanc.data.model.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("jwt")
    val jwt: String?,
    @SerializedName("userId")
    val userId: Int
)