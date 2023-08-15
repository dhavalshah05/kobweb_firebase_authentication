import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import kotlinx.html.script

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    // alias(libs.plugins.kobwebx.markdown)
}

group = "com.starter.app"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            description.set("Starter Kobweb")
            head.add {
                link {
                    href = "https://fonts.googleapis.com/css2?family=Lato:wght@400;700;900&family=Rubik:wght@300;400;500;600;700&display=swap"
                    rel = "stylesheet"
                }
                link {
                    href = "https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css"
                    rel = "stylesheet"
                    type = "text/css"
                }
                script {
                    type = "text/javascript"
                    src = "https://cdn.jsdelivr.net/npm/toastify-js"
                }
            }
        }
    }
}

kotlin {
    configAsKobwebApplication("app")

    @Suppress("UNUSED_VARIABLE") // Suppress spurious warnings about sourceset variables not being used
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation("io.insert-koin:koin-core:3.4.0")
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk.core)
                implementation(libs.kobweb.silk.icons.fa)
                // implementation(libs.kobwebx.markdown)

                implementation(npm("firebase", "10.1.0"))
            }
        }
    }
}
