package com.blanc.data.model.response


import com.google.gson.annotations.SerializedName

data class TodayDiaryResponse(
    @SerializedName("content")
    val content: String?,
    @SerializedName("isShared")
    val isShared: Boolean
)