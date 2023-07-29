package com.blanc.data.remote

import com.blanc.data.model.response.DiaryResponse
import com.blanc.domain.diary.entity.DiaryId

interface DiaryRemoteDataSource {

    suspend fun createDiary(content: String): DiaryId

    suspend fun getDiary(id: DiaryId): DiaryResponse.Data
}

