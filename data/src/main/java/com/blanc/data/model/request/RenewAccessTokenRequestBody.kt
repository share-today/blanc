package com.blanc.data.model.request


import com.google.gson.annotations.SerializedName

data class RenewAccessTokenRequestBody(
    @SerializedName("sns")
    val sns: String,
    @SerializedName("accessToken")
    val accessToken: String
)