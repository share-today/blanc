package com.wswon.blanc.login

import android.content.Context
import com.blanc.common.WLog
import com.blanc.domain.user.entity.AccessToken
import com.blanc.domain.user.entity.Email
import com.blanc.domain.user.entity.RefreshToken
import com.blanc.domain.user.entity.SnsType
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

//OAuthToken(
//    accessToken=7SJhhjZVhzdeckmcXxHjA2e-3DRovyxMWA8haVVPCj10aAAAAYfWM9i7,
//    accessTokenExpiresAt=Tue May 02 04:25:33 GMT+09:00 2023,
//    refreshToken=58Wy-c5zeBaLBokuriyxX2CqYvBVmER0khiqbEJiCj10aAAAAYfWM9i5,
//    refreshTokenExpiresAt=Fri Jun 30 16:25:33 GMT+09:00 2023,
//    idToken=null,
//    scopes=[account_email, profile_nickname]
//)
//AccountInfo(
//    id=2760460731,
//    email=wos94@naver.com,
//    nickname=우석
//)
object KakaoLoginManager {

    data class AccountInfo(
        val id: Long,
        val email: String,
        val nickname: String
    ) {
        init {
            require(id > 0)
            require(email.isNotEmpty())
        }
    }

    private val userApiClient by lazy {
        UserApiClient.instance
    }

    suspend fun login(context: Context): SnsLoginResult? {
        return kotlin.runCatching {
            val oAuthToken = getOAuthToken(context).getOrThrow()
            val accountInfo = getAccountInfo()

            WLog.d("oAuthToken $oAuthToken accountInfo $accountInfo")
            SnsLoginResult(
                snsType = SnsType.Kakao,
                id = accountInfo.id.toString(),
                token = SnsLoginResult.Token(
                    token = AccessToken(oAuthToken.accessToken),
                    refreshToken = RefreshToken(oAuthToken.refreshToken),
                    expiresAt = oAuthToken.accessTokenExpiresAt
                ),
                email = Email(accountInfo.email)
            )
        }
            .onFailure {
                it.printStackTrace()
                WLog.e(it)
            }
            .getOrNull()
    }

    private suspend fun getOAuthToken(context: Context): Result<OAuthToken> {
        return if (userApiClient.isKakaoTalkLoginAvailable(context)) {
            val tokenByKakaoApp = getOAuthTokenByKakao(context)

            if (tokenByKakaoApp.isSuccess) {
                tokenByKakaoApp
            } else {
                val error = tokenByKakaoApp.exceptionOrNull()!!
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    Result.failure(error)
                } else {
                    getOAuthTokenByAccount(context)
                }
            }
        } else {
            getOAuthTokenByAccount(context)
        }
    }

    private suspend fun getOAuthTokenByKakao(context: Context): Result<OAuthToken> {
        return kotlin.runCatching {
            suspendCancellableCoroutine { continuation ->
                userApiClient.loginWithKakaoTalk(context) { token, error ->
                    if (error != null) {
                        continuation.resumeWithException(error)
                    } else if (token != null) {
                        continuation.resume(token)
                    }
                }
            }
        }
    }

    private suspend fun getOAuthTokenByAccount(context: Context): Result<OAuthToken> {
        return kotlin.runCatching {
            suspendCancellableCoroutine { continuation ->
                userApiClient.loginWithKakaoAccount(context) { token, error ->
                    if (error != null) {
                        continuation.resumeWithException(error)
                    } else if (token != null) {
                        continuation.resume(token)
                    }
                }
            }
        }
    }

    private suspend fun getAccountInfo(): AccountInfo {
        return suspendCancellableCoroutine { continuation ->
            userApiClient.me { user, error ->
                if (error != null) {
                    continuation.resumeWithException(error)
                } else if (user != null) {
                    continuation.resume(
                        AccountInfo(
                            user.id ?: -1L,
                            user.kakaoAccount?.email.orEmpty(),
                            user.kakaoAccount?.profile?.nickname.orEmpty()
                        )
                    )
                }
            }
        }
    }
}