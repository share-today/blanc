package com.blanc.data.model.request

import com.google.gson.annotations.SerializedName

data class DiaryRequest(
    @SerializedName("content")
    val content: String
)