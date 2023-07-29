package com.blanc.data.model.response


import com.google.gson.annotations.SerializedName

data class DiaryResponse(
    @SerializedName("diary")
    val diary: Data?
) {
    data class Data(
        @SerializedName("id")
        val id: Int,
        @SerializedName("user_id")
        val userId: Int,
        @SerializedName("content")
        val content: String?,
        @SerializedName("like_count")
        val likeCount: Int,
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("modified_at")
        val modifiedAt: String?,
        @SerializedName("removed_at")
        val removedAt: String?
    )
}