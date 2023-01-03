package com.blanc.data.model.response


import com.google.gson.annotations.SerializedName

data class YesterdayDiaryResponse(
    @SerializedName("toTellMe")
    val toTellMe: String?,
    @SerializedName("myStory")
    val myStory: String?,
    @SerializedName("today")
    val today: String?
)