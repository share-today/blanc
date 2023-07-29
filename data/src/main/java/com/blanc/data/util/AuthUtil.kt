package com.blanc.data.util

import com.blanc.domain.user.entity.UserId
import com.blanc.domain.user.result.LoginResult

object AuthUtil {

    var cachedLoginResult: LoginResult? = null


    fun getBearerToken(): String? {
        val jwt = cachedLoginResult?.jwt?.value ?: return null
        return "Bearer $jwt"
    }

    fun getCurrentUserId(): UserId? {
        return cachedLoginResult?.userId ?: return null
    }
}