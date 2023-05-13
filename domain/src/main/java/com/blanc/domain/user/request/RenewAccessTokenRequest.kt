package com.blanc.domain.user.request

import com.blanc.domain.user.entity.AccessToken
import com.blanc.domain.user.entity.SnsType
import com.blanc.domain.user.entity.UserId

data class RenewAccessTokenRequest(
    val userId: UserId,
    val snsType: SnsType,
    val accessToken: AccessToken
)