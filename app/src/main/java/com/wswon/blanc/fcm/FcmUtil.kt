package com.wswon.blanc.fcm

import com.blanc.common.WLog
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

object FcmUtil {

    suspend fun getToken(): Result<String> {
        return kotlin.runCatching {
            suspendCancellableCoroutine { continuation ->
                FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        continuation.resumeWithException(
                            task.exception ?: Exception("Fetching FCM registration token failed")
                        )
                        return@OnCompleteListener
                    }

                    // Get new FCM registration token
                    val token = task.result
                    continuation.resume(token)
                })
            }
        }
            .onFailure {
                WLog.e("$it")
            }
    }
}