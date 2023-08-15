package com.starter.app.data.login.authStateChange

import com.starter.app.wrappers.FirebaseUser
import com.starter.app.wrappers.onAuthStateChanged
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AuthStateObserverUseCase(
    private val auth: Any,
) {

    fun authState(): Flow<FirebaseUser?> {
        return callbackFlow {
            onAuthStateChanged(auth) {user: FirebaseUser? ->
                trySend(user)
            }

            awaitClose {

            }
        }
    }

}