package com.blanc.data.local

import com.blanc.domain.user.result.LoginResult

interface UserLocalDataSource {

    suspend fun storeLoginResult(result: LoginResult)

    suspend fun getLoginResult(): LoginResult

    suspend fun clear()
}