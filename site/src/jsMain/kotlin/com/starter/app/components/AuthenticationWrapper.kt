package com.starter.app.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.starter.app.data.login.authStateChange.AuthStateObserverUseCase
import com.starter.app.koinApplication
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.rememberPageContext
import kotlinx.browser.sessionStorage
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@Composable
fun AuthenticationWrapper(content: @Composable () -> Unit) {
    val pageContext = rememberPageContext()

    val authStateObserverUseCase = remember {
        koinApplication.koin.get<AuthStateObserverUseCase>()
    }

    val authenticated: MutableState<Boolean?> = remember {
        mutableStateOf(null)
    }

    LaunchedEffect(Unit) {
        authStateObserverUseCase.authState().collectLatest { user ->
            if (user != null) {
                authenticated.value = true
                sessionStorage.setItem("user_id", user.uid)
                sessionStorage.setItem("user_email", user.email)
            } else {
                authenticated.value = false
                sessionStorage.clear()
            }
        }
    }

    if (authenticated.value == null) {
        H1 { Text("Loading") }
    } else if (!authenticated.value!! && !pageContext.route.isLogin()) {
        pageContext.router.navigateTo("/login")
    } else if (authenticated.value!! && pageContext.route.isLogin()) {
        pageContext.router.navigateTo("/")
    } else {
        content()
    }
}

private fun PageContext.RouteInfo.isLogin(): Boolean {
    return path.contains("login")
}