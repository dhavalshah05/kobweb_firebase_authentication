package com.starter.app.data.login.google

import com.starter.app.wrappers.GoogleAuthProvider
import com.starter.app.wrappers.signInWithPopup
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LoginWithGoogleUseCase(
    private val auth: Any,
) {
    suspend fun login() {
        return suspendCoroutine { continuation ->
            val provider = GoogleAuthProvider()
            signInWithPopup(auth, provider)
                .then<dynamic> { continuation.resumeWith(Result.success(Unit)) }
                .catch { continuation.resumeWithException(it) }
        }
    }
}