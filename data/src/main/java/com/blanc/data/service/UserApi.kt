package com.blanc.data.service

import com.blanc.data.model.request.LoginRequestBody
import com.blanc.data.model.request.RenewAccessTokenRequestBody
import com.blanc.data.model.request.RenewPushTokenRequestBody
import com.blanc.data.model.request.UpdateAlertSettingRequestBody
import com.blanc.data.model.response.BaseResponse
import com.blanc.data.model.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// https://share-todaty.site

interface UserApi {

    @POST("/auth/login")
    suspend fun login(
        @Body request: LoginRequestBody
    ): BaseResponse<LoginResponse>

    @POST("/auth/logout")
    suspend fun logout(
        @Header("Authorization") token: String
    ): BaseResponse<Nothing>

    @PUT("/user/{id}/pushToken")
    suspend fun renewPushToken(
        @Path(value = "id") id: Int,
        @Body request: RenewPushTokenRequestBody,
        @Header("Authorization") token: String
    ): BaseResponse<Nothing>

    @PUT("/user/{id}/alert")
    suspend fun alert(
        @Path(value = "id") id: Int,
        @Body request: UpdateAlertSettingRequestBody,
        @Header("Authorization") token: String
    ): BaseResponse<Nothing>

    @PUT("/user/{id}/accessToken")
    suspend fun renewAccessToken(
        @Path(value = "id") id: Int,
        @Body request: RenewAccessTokenRequestBody,
        @Header("Authorization") token: String
    ): BaseResponse<Nothing>

}