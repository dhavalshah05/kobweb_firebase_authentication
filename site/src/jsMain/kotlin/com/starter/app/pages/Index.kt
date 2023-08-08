package com.starter.app.pages

import androidx.compose.runtime.*
import com.starter.app.theme.AlphaTheme
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun HomePage() {
    // TODO: Add content here
    H1(
        attrs = Modifier
            .then(AlphaTheme.typography.latoBold)
            .toAttrs()
    ) {
        Text("Starter Kobweb")
    }
}
