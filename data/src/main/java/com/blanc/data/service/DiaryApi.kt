package com.blanc.data.service

import com.blanc.data.model.request.PostDiaryRequestBody
import com.blanc.data.model.response.BaseResponse
import com.blanc.data.model.response.DiaryResponse
import com.blanc.data.model.response.PostDiaryResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface DiaryApi {

    @POST("/diary")
    suspend fun postDiary(
        @Body request: PostDiaryRequestBody,
        @Header("Authorization") token: String
    ): BaseResponse<PostDiaryResponse>

    @GET("/diary/{id}")
    suspend fun getDiary(
        @Path(value = "id") id: Int,
        @Header("Authorization") token: String
    ): BaseResponse<DiaryResponse>

}