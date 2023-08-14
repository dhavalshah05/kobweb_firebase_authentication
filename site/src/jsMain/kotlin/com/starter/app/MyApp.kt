package com.starter.app

import androidx.compose.runtime.*
import com.starter.app.wrappers.initializeApp
import com.varabyte.kobweb.compose.css.BoxSizing
import com.varabyte.kobweb.compose.css.LineHeight
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.init.registerBaseStyle

import org.jetbrains.compose.web.css.*

@InitSilk
fun updateTheme(ctx: InitSilkContext) {
    // Configure silk here
    ctx.stylesheet.registerBaseStyle("*") {
        Modifier
            .boxSizing(BoxSizing.BorderBox)
            .borderWidth(0.px)
            .borderStyle(LineStyle.Solid)
            .margin(0.px)
    }

    ctx.stylesheet.registerBaseStyle("html") {
        Modifier
            .lineHeight(1.4)
    }

    ctx.stylesheet.registerBaseStyle("body") {
        Modifier
            .fontFamily(
                "-apple-system", "BlinkMacSystemFont", "Segoe UI", "Roboto", "Oxygen", "Ubuntu",
                "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", "sans-serif"
            )
            .margin(0.px)
            .lineHeight(LineHeight.Inherit)
    }
}

private val firebaseConfig = js("""
        {
            apiKey: "AIzaSyAuVUY_rDs6-UMOQ_0iAic9ipuoINAOupY",
            authDomain: "sample-transactions-9f089.firebaseapp.com",
            projectId: "sample-transactions-9f089",
            storageBucket: "sample-transactions-9f089.appspot.com",
            messagingSenderId: "345001372729",
            appId: "1:345001372729:web:5dca1cc09d16ab4df231e8",
            measurementId: "G-YSTVZJ8GP9"
        }
    """)

// Initialize Firebase
val firebaseApp = initializeApp(firebaseConfig)

@App
@Composable
fun MyApp(content: @Composable () -> Unit) {
    SilkApp {
        Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) {
            content()
        }
    }
}
