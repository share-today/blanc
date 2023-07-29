package com.blanc.domain.diary.entity

import com.blanc.domain.user.entity.UserId
import java.util.Date

data class Diary(
    val id: DiaryId,
    val userId: UserId,
    val content: String,
    val likeCount: Int,
    val createdAt: Date?,
    val modifiedAt: Date?,
    val removedAt: Date?
)