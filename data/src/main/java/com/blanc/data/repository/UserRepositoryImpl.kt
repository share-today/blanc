package com.blanc.data.repository

import com.blanc.data.model.request.LoginApiRequest
import com.blanc.data.model.request.RenewAccessTokenApiRequest
import com.blanc.data.model.request.RenewPushTokenApiRequest
import com.blanc.data.model.request.UpdateAlertSettingApiRequest
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
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun login(request: LoginRequest): LoginResult {
        val loginResponse = userRemoteDataSource.login(
            LoginApiRequest(
                sns = request.snsType.snsName,
                snsId = request.snsId,
                email = request.email.value,
                accessToken = request.accessToken.value
            )
        )

        return LoginResult(
            UserId(loginResponse.userId),
            JsonWebToken(loginResponse.jwt.orEmpty())
        )
    }

    override suspend fun logout(): ResultMessage {
        return ResultMessage(userRemoteDataSource.logout())
    }

    override suspend fun renewPushToken(request: RenewPushTokenRequest): ResultMessage {
        return ResultMessage(
            userRemoteDataSource.renewPushToken(
                request.userId.value,
                RenewPushTokenApiRequest(request.pushToken.value)
            )
        )
    }

    override suspend fun updateAlertSetting(request: UpdateAlertSettingRequest): ResultMessage {
        return ResultMessage(
            userRemoteDataSource.updateAlertSetting(
                request.userId.value,
                UpdateAlertSettingApiRequest(
                    request.isAlertEnable
                )
            )
        )
    }

    override suspend fun renewAccessToken(request: RenewAccessTokenRequest): ResultMessage {
        return ResultMessage(
            userRemoteDataSource.renewAccessToken(
                request.userId.value,
                RenewAccessTokenApiRequest(
                    request.snsType.snsName,
                    request.accessToken.value
                )
            )
        )
    }
}