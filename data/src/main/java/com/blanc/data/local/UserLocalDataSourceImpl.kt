package com.blanc.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.blanc.data.qualifier.UserDataStore
import com.blanc.data.util.AuthUtil
import com.blanc.domain.user.entity.JsonWebToken
import com.blanc.domain.user.entity.UserId
import com.blanc.domain.user.result.LoginResult
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    @UserDataStore private val dataStore: DataStore<Preferences>
) : UserLocalDataSource {

    private val jwtKey = stringPreferencesKey(KEY_JWT)
    private val userIdKey = intPreferencesKey(KEY_USER_ID)

    override suspend fun storeLoginResult(result: LoginResult) {
        AuthUtil.cachedLoginResult = null

        dataStore.edit { preferences ->
            preferences[jwtKey] = result.jwt.value
            preferences[userIdKey] = result.userId.value
        }
    }

    override suspend fun getLoginResult(): LoginResult {
        return AuthUtil.cachedLoginResult ?: run {
            val preferences = dataStore.data.first()
            val jwt = requireNotNull(preferences[jwtKey])
            val userId = requireNotNull(preferences[userIdKey])
            val loginResult = LoginResult(UserId(userId), JsonWebToken(jwt))

            AuthUtil.cachedLoginResult = loginResult
            loginResult
        }
    }

    override suspend fun clear() {
        AuthUtil.cachedLoginResult = null
        dataStore.edit { preferences ->
            preferences.remove(jwtKey)
            preferences.remove(userIdKey)
        }
    }

    companion object {
        private const val KEY_JWT = "jwt"
        private const val KEY_USER_ID = "user_id"
    }
}