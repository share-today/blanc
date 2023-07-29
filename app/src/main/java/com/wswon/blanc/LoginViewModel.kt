package com.wswon.blanc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blanc.common.WLog
import com.blanc.data.util.AuthUtil
import com.blanc.domain.user.entity.JsonWebToken
import com.blanc.domain.user.repository.UserRepository
import com.blanc.domain.user.request.LoginRequest
import com.blanc.domain.user.result.LoginResult
import com.wswon.blanc.login.SnsLoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    init {
        WLog.d("LoginViewModel init ${hashCode()}")
    }

    var jwt: JsonWebToken? = null
        private set

    private val _loginResult = MutableSharedFlow<LoginResult?>()
    val loginResult: SharedFlow<LoginResult?> = _loginResult.asSharedFlow()


    fun login(result: SnsLoginResult) {
        viewModelScope.launch {
            kotlin.runCatching {
                userRepository.login(
                     LoginRequest(
                        snsId = result.id,
                        snsType = result.snsType,
                        email = result.email,
                        accessToken = result.token.token
                    )
                )
            }
                .onSuccess {
                    jwt = it.jwt
                    AuthUtil.cachedLoginResult = it
                    _loginResult.emit(it)
                    WLog.d("SnsLoginResult $it")
                }
                .onFailure(WLog::e)
        }
    }

    fun logout() {
        viewModelScope.launch {
            kotlin.runCatching {
                userRepository.logout()
            }
                .onSuccess {
                    WLog.d("SnsLoginResult $it")
                    AuthUtil.cachedLoginResult = null
                    _loginResult.emit(null)
                }
                .onFailure {
                    it.printStackTrace()
                    WLog.e(it)
                }
        }
    }
}