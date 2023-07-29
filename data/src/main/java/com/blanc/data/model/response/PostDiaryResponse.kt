package com.blanc.data.model.response

import com.google.gson.annotations.SerializedName

class PostDiaryResponse(
    @SerializedName("diaryId")
    val diaryId: Int
)