package com.starter.app.data.logout

import com.starter.app.wrappers.signOut
import kotlinx.coroutines.await

class LogoutUseCase(
    private val auth: Any,
) {
    suspend fun logout() {
        signOut(auth).await()
    }
}