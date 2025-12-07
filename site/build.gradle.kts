import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import com.varabyte.kobweb.gradle.core.util.importCss
import kotlinx.html.*
import kotlinx.serialization.json.Json
import java.net.URL

plugins {
    kotlin("plugin.serialization") version "2.2.20"
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
                style {
                    importCss("/reset.css", layerName = "reset")
                }

                fetchLink("/data/selected_poems.json", rel = "prefetch", type = "application/json")
                // Google font
                link(href = "https://fonts.googleapis.com", rel = "preconnect")
                link(href = "https://fonts.gstatic.com", rel = "preconnect") {
                    attributes["crossorigin"] = "anonymous"
                }
                link(
                    href = "https://fonts.googleapis.com/css2?family=Space+Mono&display=swapfamily=Nunito+Sans:wght@200&family=Sacramento&display=swap&family=Lumanosimo&family=Quintessential&display=swap",
                    rel = "stylesheet"
                )

                // Embed for... Discord and other things
                meta(name = "color-scheme", content = "light dark")
                meta(name = "og:title", content = "Maya's page")
                meta(name = "og:description", content = "unradical. yeah. ain't rocking. bogus")
                meta(name = "og:url", content = "https://mayachen350.github.io")
                meta(name = "og:image", content = "https://avatars.githubusercontent.com/u/145165822")
                meta(name = "theme-color", content = "#efc256") {
                    attributes["data-react-helmet"] = "true"
                }

                unsafe {
                    raw(
                        "<style>\n" +
                                ":root {\n" +
                                "font-size: 62.5%;\n" +
                                "}" +
                                "</style>\n"
                    )
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

            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            // This default template uses built-in SVG icons, but what's available is limited.
            // Uncomment the following if you want access to a large set of font-awesome icons:
            // implementation(libs.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
//            implementation(libs.ksoup) // to use one day perhaps if it works on kotlin/js properly
//            implementation(libs.ksoup.network)
        }

        // Uncomment the following if you pass `includeServer = true` into the `configAsKobwebApplication` call.
//        jvmMain.dependencies {
//            compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime
//        }
    }
}

tasks.register("buildPoems") {
    group = "Other"
    description = "Build poems list json"

    val poemsFile: String =
        "https://raw.githubusercontent.com/MayaChen350/Mayascope/refs/heads/master/composeApp/src/commonMain/composeResources/files/poems.txt"
    val selectedPoemIndexes: IntArray = intArrayOf(1, 2, 3, 4, 5).map { it - 1 }.toIntArray()

    File(projectDir, "src/jsMain/resources/public/data/selected_poems.json").run {
        parentFile.mkdirs()
        writeText(
            run {
                val poems: List<List<String>> = URL(poemsFile).readText()
                    .split("///")
                    .map {
                        it.trim().split("\n\n")
                    }

                Json.encodeToString(
                    poems.filterIndexed { i, _ ->
                        selectedPoemIndexes.contains(i)
                    }
                )
            }
        )
    }
}