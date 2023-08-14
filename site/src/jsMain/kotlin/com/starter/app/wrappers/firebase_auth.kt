@file:JsModule("firebase/auth")
@file:JsNonModule

package com.starter.app.wrappers

import kotlin.js.Promise

external fun getAuth(config: dynamic): dynamic
external fun signInWithEmailAndPassword(auth: dynamic, email: String, password: String): Promise<dynamic>
external fun createUserWithEmailAndPassword(auth: dynamic, email: String, password: String): Promise<dynamic>
external fun onAuthStateChanged(auth: dynamic, callback: (user: dynamic) -> Unit)
external fun signOut(auth: dynamic): Promise<dynamic>

external class GoogleAuthProvider {

}
external fun signInWithPopup(auth: dynamic, provider: dynamic): Promise<dynamic>
