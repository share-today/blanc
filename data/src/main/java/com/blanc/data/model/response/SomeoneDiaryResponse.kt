package com.blanc.data.model.response


import com.google.gson.annotations.SerializedName

data class SomeoneDiaryResponse(
    @SerializedName("someoneStory")
    val someoneStory: String?,
    @SerializedName("toTellSomeone")
    val toTellSomeone: String?,
    @SerializedName("today")
    val today: String?
)