package com.blanc.data.model.request


import com.google.gson.annotations.SerializedName

data class RenewPushTokenApiRequest(
    @SerializedName("push_token")
    val pushToken: String
)