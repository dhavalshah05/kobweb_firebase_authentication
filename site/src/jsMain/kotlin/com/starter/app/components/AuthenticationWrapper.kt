package com.starter.app.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.starter.app.firebaseApp
import com.starter.app.wrappers.getAuth
import com.starter.app.wrappers.onAuthStateChanged
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.rememberPageContext
import kotlinx.browser.sessionStorage
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@Composable
fun AuthenticationWrapper(content: @Composable () -> Unit) {
    val pageContext = rememberPageContext()
    val auth = getAuth(firebaseApp)
    val authenticated: MutableState<Boolean?> = remember {
        mutableStateOf(null)
    }

    LaunchedEffect(Unit) {
        onAuthStateChanged(auth) { user ->
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