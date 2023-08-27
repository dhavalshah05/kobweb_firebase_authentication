package com.starter.app.theme.color

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.web.css.CSSColorValue

@Immutable
data class AlphaColors(
    val primary: CSSColorValue,
    val darkest: CSSColorValue,
    val dark: CSSColorValue,
    val medium: CSSColorValue,
    val light: CSSColorValue,
    val lightest: CSSColorValue,
    val white: CSSColorValue,
)
