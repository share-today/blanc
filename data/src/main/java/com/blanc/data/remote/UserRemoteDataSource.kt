package com.blanc.data.remote

import com.blanc.data.model.request.LoginRequestBody
import com.blanc.data.model.request.RenewAccessTokenRequestBody
import com.blanc.data.model.request.RenewPushTokenRequestBody
import com.blanc.data.model.request.UpdateAlertSettingRequestBody
import com.blanc.data.model.response.LoginResponse

interface UserRemoteDataSource {

    suspend fun login(
        request: LoginRequestBody
    ): LoginResponse

    suspend fun logout(): String

    suspend fun renewPushToken(
        request: RenewPushTokenRequestBody
    ): String

    suspend fun updateAlertSetting(
        request: UpdateAlertSettingRequestBody
    ): String

    suspend fun renewAccessToken(
        request: RenewAccessTokenRequestBody
    ): String
}

