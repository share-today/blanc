package com.blanc.domain.user.repository

import com.blanc.domain.shared.entity.ResultMessage
import com.blanc.domain.user.request.LoginRequest
import com.blanc.domain.user.request.RenewAccessTokenRequest
import com.blanc.domain.user.request.RenewPushTokenRequest
import com.blanc.domain.user.request.UpdateAlertSettingRequest
import com.blanc.domain.user.result.LoginResult

interface UserRepository {

    suspend fun login(
        request: LoginRequest
    ): LoginResult

    suspend fun logout(): ResultMessage

    suspend fun renewPushToken(
        request: RenewPushTokenRequest
    ): ResultMessage

    suspend fun updateAlertSetting(
        request: UpdateAlertSettingRequest
    ): ResultMessage

    suspend fun renewAccessToken(
        request: RenewAccessTokenRequest
    ): ResultMessage
}