package com.blanc.domain.user.request

import com.blanc.domain.user.entity.AccessToken
import com.blanc.domain.user.entity.Email
import com.blanc.domain.user.entity.SnsType

data class LoginRequest(
    val snsId: String,
    val snsType: SnsType,
    val email: Email,
    val accessToken: AccessToken
)