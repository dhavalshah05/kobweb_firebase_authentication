package com.starter.app.pages

import androidx.compose.runtime.*
import com.starter.app.components.AuthenticationWrapper
import com.starter.app.theme.AlphaTheme
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import kotlinx.browser.sessionStorage
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.get

@Page
@Composable
fun HomePage() {
    val userId = remember {
        sessionStorage["user_id"]
    }

    AuthenticationWrapper {
        H1(
            attrs = Modifier
                .then(AlphaTheme.typography.latoBold)
                .toAttrs()
        ) {
            Text("Welcome $userId")
        }
    }
}
