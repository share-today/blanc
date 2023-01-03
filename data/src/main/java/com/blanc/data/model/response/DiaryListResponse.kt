package com.blanc.data.model.response


import com.google.gson.annotations.SerializedName

data class DiaryListResponse(
    @SerializedName("dataList")
    val dataList: List<String>?
)