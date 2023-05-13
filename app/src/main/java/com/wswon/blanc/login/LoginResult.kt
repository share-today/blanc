package com.wswon.blanc.login

import com.blanc.domain.user.entity.AccessToken
import com.blanc.domain.user.entity.Email
import com.blanc.domain.user.entity.RefreshToken
import com.blanc.domain.user.entity.SnsType
import java.util.Date

data class LoginResult(
    val snsType: SnsType,
    val id: String,
    val token: Token,
    val email: Email,
) {

    data class Token(
        val token: AccessToken,
        val refreshToken: RefreshToken? = null,
        val expiresAt: Date? = null,
    )
}