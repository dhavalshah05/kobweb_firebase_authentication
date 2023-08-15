package com.starter.app.di

import com.starter.app.data.login.authStateChange.AuthStateObserverUseCase
import com.starter.app.data.login.emailAndPassword.LoginWithPasswordUseCase
import com.starter.app.data.login.google.LoginWithGoogleUseCase
import com.starter.app.data.logout.LogoutUseCase
import com.starter.app.wrappers.getAuth
import com.starter.app.wrappers.initializeApp
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

object KoinQualifiers {
    val FirebaseConfig = StringQualifier("FirebaseConfig")
    val FirebaseApp = StringQualifier("FirebaseApp")
    val FirebaseAuth = StringQualifier("FirebaseAuth")
}

@Suppress("RemoveExplicitTypeArguments")
object KoinModules {

    val applicationModule = module {

        single<Any>(qualifier = KoinQualifiers.FirebaseConfig) {
            js("""
                {
                    apiKey: "AIzaSyAuVUY_rDs6-UMOQ_0iAic9ipuoINAOupY",
                    authDomain: "sample-transactions-9f089.firebaseapp.com",
                    projectId: "sample-transactions-9f089",
                    storageBucket: "sample-transactions-9f089.appspot.com",
                    messagingSenderId: "345001372729",
                    appId: "1:345001372729:web:5dca1cc09d16ab4df231e8",
                    measurementId: "G-YSTVZJ8GP9"
                }
            """) as Any
        }

        single<Any>(qualifier = KoinQualifiers.FirebaseApp) {
            val config: Any = get(qualifier = KoinQualifiers.FirebaseConfig)
            initializeApp(config) as Any
        }

        single<Any>(qualifier = KoinQualifiers.FirebaseAuth) {
            val firebaseApp: Any = get(qualifier = KoinQualifiers.FirebaseApp)
            getAuth(firebaseApp) as Any
        }

    }

    val useCaseModule = module {

        factory<LoginWithGoogleUseCase> {
            val auth: Any = get(qualifier = KoinQualifiers.FirebaseAuth)
            LoginWithGoogleUseCase(auth)
        }

        factory<LoginWithPasswordUseCase> {
            val auth: Any = get(qualifier = KoinQualifiers.FirebaseAuth)
            LoginWithPasswordUseCase(auth)
        }

        factory<LogoutUseCase> {
            val auth: Any = get(qualifier = KoinQualifiers.FirebaseAuth)
            LogoutUseCase(auth)
        }

        factory<AuthStateObserverUseCase> {
            val auth: Any = get(qualifier = KoinQualifiers.FirebaseAuth)
            AuthStateObserverUseCase(auth)
        }
    }
}