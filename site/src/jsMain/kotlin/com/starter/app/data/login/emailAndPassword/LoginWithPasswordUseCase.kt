package com.starter.app.data.login.emailAndPassword

import com.starter.app.wrappers.signInWithEmailAndPassword
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LoginWithPasswordUseCase(
    private val auth: Any
) {
    suspend fun login(email: String, password: String) {
        return suspendCoroutine { continuation ->
            signInWithEmailAndPassword(auth, email, password)
                .then<dynamic> {
                    continuation.resumeWith(Result.success(Unit))
                }
                .catch {
                    continuation.resumeWithException(it)
                }
        }
    }
}
