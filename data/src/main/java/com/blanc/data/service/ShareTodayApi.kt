package com.blanc.data.service

import com.blanc.data.model.request.DiaryRequest
import com.blanc.data.model.response.DiaryListResponse
import com.blanc.data.model.response.SomeoneDiaryResponse
import com.blanc.data.model.response.YesterdayDiaryResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ShareTodayApi {

    @GET("/api/diary-storage/date-list")
    suspend fun getDiaryList(): DiaryListResponse

    @GET("/api/home/focus-on-me")
    suspend fun getYesterdayDiary(): YesterdayDiaryResponse

    @GET("/api/home/focus-on-someone")
    suspend fun getSomeoneDiary(): SomeoneDiaryResponse

    @POST("/api/home/for-someone")
    suspend fun postDiary(
        @Body request: DiaryRequest
    ): String

    @GET("/api/home/today-diary")
    suspend fun getTodayDiary()
}

