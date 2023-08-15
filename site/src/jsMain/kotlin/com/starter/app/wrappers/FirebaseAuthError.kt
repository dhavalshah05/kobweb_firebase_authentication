package com.starter.app.wrappers

enum class FirebaseAuthError {
    USER_DISABLED,
    USER_NOT_FOUND,
    INVALID_PASSWORD,
}

fun decodeFirebaseAuthError(throwable: Throwable): FirebaseAuthError {
    val message = throwable.message
        ?: throw Exception("Unable to decode FirebaseAuth Error for ${throwable.message}")

    return when {
        message.contains("auth/user-disabled") -> FirebaseAuthError.USER_DISABLED
        message.contains("auth/wrong-password") -> FirebaseAuthError.INVALID_PASSWORD
        message.contains("auth/user-not-found") -> FirebaseAuthError.USER_NOT_FOUND
        else -> throw Exception("Unable to decode FirebaseAuth Error for ${throwable.message}")
    }
}