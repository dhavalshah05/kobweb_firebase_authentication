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
            background = rgb(255, 255, 255),
            primary = rgb(97 , 106 , 242),
            gray50 = rgb(19 , 44 , 71),
            gray30 = rgb(132, 149,160),
            white = rgb(255, 255,255),
            orange = rgb(255, 133, 120),
            backgroundFooter = rgb(242, 248, 252),
            grayText = rgb(225, 236, 243),
            grayText80 = rgba(225, 236, 243, 0.8),
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
