package com.starter.app.pages

import androidx.compose.runtime.*
import com.starter.app.components.AuthenticationWrapper
import com.starter.app.data.login.google.LoginWithGoogleUseCase
import com.starter.app.data.login.emailAndPassword.LoginWithPasswordUseCase
import com.starter.app.firebaseApp
import com.starter.app.wrappers.getAuth
import com.starter.app.theme.AlphaTheme
import com.starter.app.wrappers.decodeFirebaseAuthError
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
    val loginWithGoogleUseCase = remember {
        LoginWithGoogleUseCase(auth)
    }

    H1(
        attrs = Modifier
            .then(AlphaTheme.typography.latoBold)
            .onClick {
                scope.launch {
                    try {
                        loginWithGoogleUseCase.login()
                    } catch (e: Throwable) {
                        println("Login with Google error: ${e.message}")
                    }
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
    val loginWithPasswordUseCase = remember {
        LoginWithPasswordUseCase(auth)
    }

    H1(
        attrs = Modifier
            .then(AlphaTheme.typography.latoBold)
            .onClick {
                scope.launch {
                    try {
                        loginWithPasswordUseCase.login(
                            email = "dhaval3@gmail.com",
                            password = "Abc@123#"
                        )
                    } catch (e: Throwable) {
                        val authError = decodeFirebaseAuthError(e)
                        println("Login with Password error: $authError")
                    }
                }
            }
            .toAttrs()
    ) {
        Text("Click to login with Password")
    }
}



