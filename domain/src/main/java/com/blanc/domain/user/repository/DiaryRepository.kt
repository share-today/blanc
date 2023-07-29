package com.blanc.domain.user.repository

import com.blanc.domain.diary.entity.Diary
import com.blanc.domain.diary.entity.DiaryId

interface DiaryRepository {

    suspend fun createDiary(content: String): DiaryId

    suspend fun getDiary(id: DiaryId): Diary
}