package com.blanc.domain.user.request

import com.blanc.domain.user.entity.UserId

data class UpdateAlertSettingRequest(
    val userId: UserId,
    val isAlertEnable: Boolean
)