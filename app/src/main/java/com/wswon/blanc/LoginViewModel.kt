package com.wswon.blanc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blanc.common.WLog
import com.blanc.domain.user.entity.JsonWebToken
import com.blanc.domain.user.repository.UserRepository
import com.blanc.domain.user.request.LoginRequest
import com.wswon.blanc.login.SnsLoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var jwt: JsonWebToken? = null
        private set

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
                }
                .onFailure(WLog::e)
        }
    }
}