package com.blanc.data.repository

import com.blanc.data.local.UserLocalDataSource
import com.blanc.data.model.request.LoginRequestBody
import com.blanc.data.model.request.RenewAccessTokenRequestBody
import com.blanc.data.model.request.RenewPushTokenRequestBody
import com.blanc.data.model.request.UpdateAlertSettingRequestBody
import com.blanc.data.remote.UserRemoteDataSource
import com.blanc.domain.shared.entity.ResultMessage
import com.blanc.domain.user.entity.JsonWebToken
import com.blanc.domain.user.entity.UserId
import com.blanc.domain.user.repository.UserRepository
import com.blanc.domain.user.request.LoginRequest
import com.blanc.domain.user.request.RenewAccessTokenRequest
import com.blanc.domain.user.request.RenewPushTokenRequest
import com.blanc.domain.user.request.UpdateAlertSettingRequest
import com.blanc.domain.user.result.LoginResult
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {

    override suspend fun login(request: LoginRequest): LoginResult {
        val loginResponse = userRemoteDataSource.login(
            LoginRequestBody(
                sns = request.snsType.snsName,
                snsId = request.snsId,
                email = request.email.value,
                accessToken = request.accessToken.value
            )
        )

        val loginResult = LoginResult(
            UserId(loginResponse.userId),
            JsonWebToken(loginResponse.jwt.orEmpty())
        )

        userLocalDataSource.storeLoginResult(loginResult)
        return loginResult
    }

    override suspend fun logout(): ResultMessage {
        val message = userRemoteDataSource.logout()
        userLocalDataSource.clear()
        return ResultMessage(message)
    }

    override suspend fun renewPushToken(request: RenewPushTokenRequest): ResultMessage {
        return ResultMessage(
            userRemoteDataSource.renewPushToken(
                RenewPushTokenRequestBody(request.pushToken.value)
            )
        )
    }

    override suspend fun updateAlertSetting(request: UpdateAlertSettingRequest): ResultMessage {
        return ResultMessage(
            userRemoteDataSource.updateAlertSetting(
                UpdateAlertSettingRequestBody(
                    request.isAlertEnable
                )
            )
        )
    }

    override suspend fun renewAccessToken(request: RenewAccessTokenRequest): ResultMessage {
        return ResultMessage(
            userRemoteDataSource.renewAccessToken(
                RenewAccessTokenRequestBody(
                    request.snsType.snsName,
                    request.accessToken.value
                )
            )
        )
    }
}