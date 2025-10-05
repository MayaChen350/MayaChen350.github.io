import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import kotlinx.html.meta

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
}

group = "io.github.mayachen350.website"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            head.add {
                link(href = "/reset.css", rel = "stylesheet")

                // Google font
                link(href = "https://fonts.googleapis.com", rel = "preconnect")
                link(href = "https://fonts.gstatic.com", rel = "preconnect") {
                    attributes["crossorigin"] = "anonymous"
                }
                link(
                    href = "https://fonts.googleapis.com/css2?family=Space+Mono&display=swapfamily=Nunito+Sans:wght@200&family=Sacramento&display=swap&family=Lumanosimo&display=swap",
                    rel = "stylesheet"
                )

                // Embed for... Discord and other things
                meta(name = "color-scheme", content = "light dark")
                meta(name = "og:title", content = "Maya's page")
                meta(name = "og:description", content = "unradical. yeah. ain't rocking. bogus")
                meta(name = "og:url", content = "https://mayachen350.github.io")
                meta(name = "og:image", content = "https://avatars.githubusercontent.com/u/145165822")
                meta(name = "theme-color", content = "#d7060b") {
                    attributes["data-react-helmet"] = "true"
                }
            }
            // TODO: Add favicon one day
            description.set("My page!!! Did you know that in terms of computer science, Porygon is...")
        }
    }
}

kotlin {
    // This example is frontend only. However, for a fullstack app, you can uncomment the includeServer parameter
    // and the `jvmMain` source set below.
    configAsKobwebApplication("website" /*, includeServer = true*/)

    sourceSets {
//        commonMain.dependencies {
//          // Add shared dependencies between JS and JVM here if building a fullstack app
//        }

        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            // This default template uses built-in SVG icons, but what's available is limited.
            // Uncomment the following if you want access to a large set of font-awesome icons:
            // implementation(libs.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
        }

        // Uncomment the following if you pass `includeServer = true` into the `configAsKobwebApplication` call.
//        jvmMain.dependencies {
//            compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime
//        }
    }
}
