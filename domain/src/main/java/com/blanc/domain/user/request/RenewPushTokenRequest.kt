package com.blanc.domain.user.request

import com.blanc.domain.user.entity.PushToken
import com.blanc.domain.user.entity.UserId

data class RenewPushTokenRequest(
    val userId: UserId,
    val pushToken: PushToken
)