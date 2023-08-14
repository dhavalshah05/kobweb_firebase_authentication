package com.starter.app.pages

import androidx.compose.runtime.*
import com.starter.app.components.AuthenticationWrapper
import com.starter.app.firebaseApp
import com.starter.app.wrappers.getAuth
import com.starter.app.wrappers.signInWithEmailAndPassword
import com.starter.app.theme.AlphaTheme
import com.starter.app.wrappers.GoogleAuthProvider
import com.starter.app.wrappers.signInWithPopup
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@Page("login")
@Composable
fun LoginPage() {
    AuthenticationWrapper {
        val auth = remember {
            getAuth(firebaseApp)
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LoginWithPasswordOption(auth)
                LoginWithGoogleOption(auth)
            }
        }
    }
}

@Composable
private fun LoginWithGoogleOption(auth: Any) {
    val scope = rememberCoroutineScope()

    H1(
        attrs = Modifier
            .then(AlphaTheme.typography.latoBold)
            .onClick {
                scope.launch {
                    val provider = GoogleAuthProvider()
                    signInWithPopup(auth, provider)
                        .then<dynamic> { println("Login Success") }
                        .catch { it.printStackTrace() }

                }
            }
            .toAttrs()
    ) {
        Text("Click to login with Google")
    }
}

@Composable
private fun LoginWithPasswordOption(auth: Any) {
    val scope = rememberCoroutineScope()

    H1(
        attrs = Modifier
            .then(AlphaTheme.typography.latoBold)
            .onClick {
                scope.launch {
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
        Text("Click to login with Password")
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