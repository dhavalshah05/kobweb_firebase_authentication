package com.starter.app.util

import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.navigation.open
import org.w3c.dom.HTMLElement
import org.w3c.dom.SMOOTH
import org.w3c.dom.ScrollToOptions
import org.w3c.dom.Window

fun Window.openUrlInNewTab(url: String) {
    this.open(
        href = url,
        strategy = OpenLinkStrategy.IN_NEW_TAB
    )
}

fun Window.smoothScrollToElement(elementId: String, paddingTop: Int) {
    val element = document.getElementById(elementId) as? HTMLElement
    if (element != null) {
        val scrollPosition = element.offsetTop - paddingTop
        this.scroll(ScrollToOptions(top = scrollPosition.toDouble(), behavior = org.w3c.dom.ScrollBehavior.SMOOTH))
    }
}
