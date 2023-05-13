package com.blanc.data.remote

import com.blanc.data.model.request.LoginApiRequest
import com.blanc.data.model.request.RenewAccessTokenApiRequest
import com.blanc.data.model.request.RenewPushTokenApiRequest
import com.blanc.data.model.request.UpdateAlertSettingApiRequest
import com.blanc.data.model.response.LoginResponse

interface UserRemoteDataSource {

    suspend fun login(
        request: LoginApiRequest
    ): LoginResponse

    suspend fun logout(): String

    suspend fun renewPushToken(
        userId: Int,
        request: RenewPushTokenApiRequest
    ): String

    suspend fun updateAlertSetting(
        userId: Int,
        request: UpdateAlertSettingApiRequest
    ): String

    suspend fun renewAccessToken(
        userId: Int,
        request: RenewAccessTokenApiRequest
    ): String
}

