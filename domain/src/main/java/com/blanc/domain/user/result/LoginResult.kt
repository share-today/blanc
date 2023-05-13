package com.blanc.domain.user.result

import com.blanc.domain.user.entity.JsonWebToken
import com.blanc.domain.user.entity.UserId

data class LoginResult(
    val userId: UserId,
    val jwt: JsonWebToken
)