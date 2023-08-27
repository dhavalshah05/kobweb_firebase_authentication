package com.starter.app.theme

import com.starter.app.theme.color.AlphaColors
import com.starter.app.theme.typography.AlphaTypography
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgb
import org.jetbrains.compose.web.css.rgba

object AlphaTheme {
    val MAX_WIDTH = 1100.px

    var colors: AlphaColors
        private set

    var typography: AlphaTypography
        private set

    init {
        colors = AlphaColors(
            primary = rgb(61 , 85 , 204),
            white = rgb(255, 255,255),
            darkest = rgb(20, 26,51),
            dark = rgb(80, 86,115),
            medium = rgb(135, 140,168),
            light = rgb(218, 222,242),
            lightest = rgb(245, 246,250),
        )

        typography = AlphaTypography(
            latoNormal = Modifier
                .fontFamily("Lato")
                .fontWeight(FontWeight.Normal),
            latoBold = Modifier
                .fontFamily("Lato")
                .fontWeight(FontWeight.Bold),
            latoBlack = Modifier
                .fontFamily("Lato")
                .fontWeight(FontWeight.Black),
        )
    }
}
