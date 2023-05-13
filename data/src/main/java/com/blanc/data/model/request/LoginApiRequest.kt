package com.blanc.data.model.request


import com.google.gson.annotations.SerializedName

data class LoginApiRequest(
    @SerializedName("sns")
    val sns: String,
    @SerializedName("snsId")
    val snsId: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("accessToken")
    val accessToken: String
)