package com.starter.app.util.toast

import androidx.compose.runtime.NoLiveLiterals

data class Toast(
    private val text: String,
    private val type: ToastType = ToastType.INFO
) {

    companion object

    @NoLiveLiterals
    fun show() {
        val toastifyOptions = js("{}")

        toastifyOptions.text = text

        toastifyOptions.newWindow = true
        toastifyOptions.close = true
        toastifyOptions.gravity = "top"
        toastifyOptions.position = "center"
        toastifyOptions.stopOnFocus = true

        val style = js("{}")
        style["background"] = when (type) {
            ToastType.INFO -> "#17a2b8"
            ToastType.ERROR -> "#dc3545"
            ToastType.WARNING -> "#ffc107"
            ToastType.SUCCESS -> "#28a745"
        }
        toastifyOptions.style = style

        toastifyOptions.onClick = { }

        val toastify = js("Toastify(toastifyOptions)")
        toastify.showToast()
    }
}

fun Toast.Companion.showSuccess(text: String) {
    Toast(text, ToastType.SUCCESS).show()
}

fun Toast.Companion.showError(text: String) {
    Toast(text, ToastType.ERROR).show()
}

fun Toast.Companion.showInfo(text: String) {
    Toast(text, ToastType.INFO).show()
}

enum class ToastType {
    INFO,
    ERROR,
    WARNING,
    SUCCESS,
}