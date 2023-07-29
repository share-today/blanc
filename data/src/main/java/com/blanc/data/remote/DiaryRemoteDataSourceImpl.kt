package com.blanc.data.remote

import com.blanc.data.model.request.PostDiaryRequestBody
import com.blanc.data.model.response.DiaryResponse
import com.blanc.data.service.DiaryApi
import com.blanc.data.util.AuthUtil
import com.blanc.domain.diary.entity.DiaryId
import com.blanc.domain.user.entity.UserId
import javax.inject.Inject

class DiaryRemoteDataSourceImpl @Inject constructor(
    private val diaryApi: DiaryApi
) : DiaryRemoteDataSource {

    private val token: String
        get() = AuthUtil.getBearerToken()!!

    private val userId: UserId
        get() = AuthUtil.getCurrentUserId()!!

    override suspend fun createDiary(content: String): DiaryId {
        val requestBody = PostDiaryRequestBody(content)
        return DiaryId(diaryApi.postDiary(requestBody, token).data.diaryId)

    }

    override suspend fun getDiary(id: DiaryId): DiaryResponse.Data {
        return requireNotNull(diaryApi.getDiary(id.value, token).data.diary)
    }
}