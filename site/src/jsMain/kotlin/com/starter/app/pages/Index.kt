package com.starter.app.pages

import androidx.compose.runtime.*
import com.starter.app.components.AuthenticationWrapper
import com.starter.app.firebaseApp
import com.starter.app.theme.AlphaTheme
import com.starter.app.wrappers.getAuth
import com.starter.app.wrappers.signOut
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
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.get

@Page
@Composable
fun HomePage() {
    val userId = remember {
        sessionStorage["user_id"]
    }

    val scope = rememberCoroutineScope()
    val auth = remember {
        getAuth(firebaseApp)
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
                                signOut(auth = auth).await()
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
