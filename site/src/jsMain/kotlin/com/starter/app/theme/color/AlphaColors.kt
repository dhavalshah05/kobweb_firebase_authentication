package com.starter.app.theme.color

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.web.css.CSSColorValue

@Immutable
data class AlphaColors(
    val background: CSSColorValue,
    val primary: CSSColorValue,
    val gray50: CSSColorValue,
    val gray30: CSSColorValue,
    val white: CSSColorValue,
    val orange: CSSColorValue,
    val backgroundFooter: CSSColorValue,
    val grayText: CSSColorValue,
    val grayText80: CSSColorValue,
)
