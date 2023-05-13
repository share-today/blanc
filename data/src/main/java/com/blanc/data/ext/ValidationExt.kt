package com.blanc.data.ext

import com.blanc.data.model.response.BaseResponse

fun BaseResponse<*>.throwIfFailed() {
    if (result) {
        throw Exception("result is false $this")
    }
}