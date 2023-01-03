package com.blanc.data.model.response


import com.google.gson.annotations.SerializedName

data class SomeoneDiaryResponse(
    @SerializedName("someoneStory")
    val someoneStory: Any?,
    @SerializedName("toTellSomeone")
    val toTellSomeone: Any?,
    @SerializedName("today")
    val today: String?
)