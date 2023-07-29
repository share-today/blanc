package com.blanc.data.remote

import com.blanc.data.ext.throwIfFailed
import com.blanc.data.model.request.LoginRequestBody
import com.blanc.data.model.request.RenewAccessTokenRequestBody
import com.blanc.data.model.request.RenewPushTokenRequestBody
import com.blanc.data.model.request.UpdateAlertSettingRequestBody
import com.blanc.data.model.response.LoginResponse
import com.blanc.data.service.UserApi
import com.blanc.data.util.AuthUtil
import com.blanc.domain.user.entity.UserId
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userApi: UserApi
) : UserRemoteDataSource {

    private val token: String
        get() = AuthUtil.getBearerToken()!!

    private val userId: UserId
        get() = AuthUtil.getCurrentUserId()!!

    override suspend fun login(request: LoginRequestBody): LoginResponse {
        val response = userApi.login(request)
        response.throwIfFailed()

        return requireNotNull(response.data)
    }

    override suspend fun logout(): String {
        val response = userApi.logout(token)
        response.throwIfFailed()

        return response.message.orEmpty()
    }

    override suspend fun renewPushToken(request: RenewPushTokenRequestBody): String {
        val response = userApi.renewPushToken(userId.value, request, token)
        response.throwIfFailed()

        return response.message.orEmpty()
    }

    override suspend fun updateAlertSetting(request: UpdateAlertSettingRequestBody): String {
        val response = userApi.alert(userId.value, request, token)
        response.throwIfFailed()

        return response.message.orEmpty()
    }

    override suspend fun renewAccessToken(request: RenewAccessTokenRequestBody): String {
        val response = userApi.renewAccessToken(userId.value, request, token)
        response.throwIfFailed()

        return response.message.orEmpty()
    }
}