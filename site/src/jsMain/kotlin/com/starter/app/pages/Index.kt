package com.starter.app.pages

import androidx.compose.runtime.*
import com.starter.app.components.AuthenticationWrapper
import com.starter.app.data.logout.LogoutUseCase
import com.starter.app.koinApplication
import com.starter.app.theme.AlphaTheme
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import kotlinx.browser.sessionStorage
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.get

@Page
@Composable
fun HomePage() {
    val userId = remember {
        sessionStorage["user_email"]
    }

    val scope = rememberCoroutineScope()

    val logoutUseCase = remember {
        koinApplication.koin.get<LogoutUseCase>()
    }

    AuthenticationWrapper {
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
                H1(
                    attrs = Modifier
                        .then(AlphaTheme.typography.latoBold)
                        .toAttrs()
                ) {
                    Text("Welcome $userId")
                }

                Button(
                    attrs = Modifier
                        .onClick {
                            scope.launch {
                                try {
                                    logoutUseCase.logout()
                                } catch (e: Throwable) {
                                    println("Logout error: ${e.message}")
                                }
                            }
                        }
                        .toAttrs()
                ){
                    Text("Logout")
                }
            }
        }

    }
}
