package com.blanc.data.remote

import com.blanc.data.ext.throwIfFailed
import com.blanc.data.model.request.LoginApiRequest
import com.blanc.data.model.request.RenewAccessTokenApiRequest
import com.blanc.data.model.request.RenewPushTokenApiRequest
import com.blanc.data.model.request.UpdateAlertSettingApiRequest
import com.blanc.data.model.response.LoginResponse
import com.blanc.data.service.UserApi
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userApi: UserApi
) : UserRemoteDataSource {

    override suspend fun login(request: LoginApiRequest): LoginResponse {
        val response = userApi.login(request)
        response.throwIfFailed()

        return requireNotNull(response.data)
    }

    override suspend fun logout(): String {
        val response = userApi.logout()
        response.throwIfFailed()

        return response.message.orEmpty()
    }

    override suspend fun renewPushToken(userId: Int, request: RenewPushTokenApiRequest): String {
        val response = userApi.renewPushToken(userId, request)
        response.throwIfFailed()

        return response.message.orEmpty()
    }

    override suspend fun updateAlertSetting(userId: Int, request: UpdateAlertSettingApiRequest): String {
        val response = userApi.alert(userId, request)
        response.throwIfFailed()

        return response.message.orEmpty()
    }

    override suspend fun renewAccessToken(
        userId: Int,
        request: RenewAccessTokenApiRequest
    ): String {
        val response = userApi.renewAccessToken(userId, request)
        response.throwIfFailed()

        return response.message.orEmpty()
    }
}