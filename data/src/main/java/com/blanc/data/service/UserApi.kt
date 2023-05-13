package com.blanc.data.service

import com.blanc.data.model.request.LoginApiRequest
import com.blanc.data.model.request.RenewAccessTokenApiRequest
import com.blanc.data.model.request.RenewPushTokenApiRequest
import com.blanc.data.model.request.UpdateAlertSettingApiRequest
import com.blanc.data.model.response.BaseResponse
import com.blanc.data.model.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// https://share-todaty.site

interface UserApi {

    @POST("/auth/login")
    suspend fun login(
        @Body request: LoginApiRequest
    ): BaseResponse<LoginResponse>

    @POST("/auth/logout")
    suspend fun logout(): BaseResponse<Nothing>

    @PUT("/user/{id}/pushToken")
    suspend fun renewPushToken(
        @Path(value = "id") id: Int,
        @Body request: RenewPushTokenApiRequest
    ): BaseResponse<Nothing>

    @PUT("/user/{id}/alert")
    suspend fun alert(
        @Path(value = "id") id: Int,
        @Body request: UpdateAlertSettingApiRequest
    ): BaseResponse<Nothing>

    @PUT("/user/{id}/accessToken")
    suspend fun renewAccessToken(
        @Path(value = "id") id: Int,
        @Body request: RenewAccessTokenApiRequest
    ): BaseResponse<Nothing>

}