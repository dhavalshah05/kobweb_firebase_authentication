package com.starter.app.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.rememberPageContext

@Composable
fun AuthenticationWrapper(content: @Composable () -> Unit) {
    val pageContext = rememberPageContext()

    val authenticated = true
    if (!authenticated && !pageContext.route.isLogin()) {
        pageContext.router.navigateTo("/login")
        return
    }

    if (authenticated && pageContext.route.isLogin()) {
        pageContext.router.navigateTo("/")
        return
    }

    content()
}

private fun PageContext.RouteInfo.isLogin(): Boolean {
    return path.contains("login")
}