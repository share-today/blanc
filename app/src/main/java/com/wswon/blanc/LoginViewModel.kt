package com.wswon.blanc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blanc.common.WLog
import com.blanc.domain.user.repository.UserRepository
import com.blanc.domain.user.request.LoginRequest
import com.wswon.blanc.login.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun login(result: LoginResult) {
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
                    WLog.d("LoginResult $it")
                }
                .onFailure(WLog::e)
        }
    }
}