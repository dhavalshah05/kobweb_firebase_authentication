package com.starter.app.pages

import androidx.compose.runtime.*
import com.starter.app.components.AuthenticationWrapper
import com.starter.app.firebaseApp
import com.starter.app.wrappers.getAuth
import com.starter.app.wrappers.signInWithEmailAndPassword
import com.starter.app.theme.AlphaTheme
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@Page("login")
@Composable
fun LoginPage() {
    val scope = rememberCoroutineScope()

    AuthenticationWrapper {
        H1(
            attrs = Modifier
                .then(AlphaTheme.typography.latoBold)
                .onClick {
                    scope.launch {
                        val auth = getAuth(firebaseApp)
                        signInWithEmailAndPassword(auth, "dhaval3@gmail.com", "Abc@123#")
                            .then<dynamic> {  }
                            .catch {
                                val error = decodeFirebaseAuthError(it)
                                println(error)
                            }
                    }
                }
                .toAttrs()
        ) {
            Text("Login Page")
        }
    }
}

enum class FirebaseAuthError {
    USER_DISABLED,
    USER_NOT_FOUND,
    INVALID_PASSWORD,
}

private fun decodeFirebaseAuthError(throwable: Throwable): FirebaseAuthError {
    val message = throwable.message
        ?: throw Exception("Unable to decode FirebaseAuth Error for ${throwable.message}")

    return when {
        message.contains("auth/user-disabled") -> FirebaseAuthError.USER_DISABLED
        message.contains("auth/wrong-password") -> FirebaseAuthError.INVALID_PASSWORD
        message.contains("auth/user-not-found") -> FirebaseAuthError.USER_NOT_FOUND
        else -> throw Exception("Unable to decode FirebaseAuth Error for ${throwable.message}")
    }
}