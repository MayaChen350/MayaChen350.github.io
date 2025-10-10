package io.github.mayachen350.website.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.browser.http.FetchDefaults
import com.varabyte.kobweb.browser.http.HttpFetcher
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderBottom
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import io.github.mayachen350.website.SitePalette
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.content
import io.ktor.http.isSuccess
import io.ktor.util.collections.setValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Aside
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.Text
import kotlin.js.json
import kotlin.time.Duration.Companion.seconds

val HeaderTitleStyle = CssStyle {
    base {
        Modifier
            .color(Colors.Black)
            .fontFamily("Sacramento")
            .fontSize(7.5.cssRem)
            .fontWeight(700)
            .padding(left = 8.cssRem, top = 2.cssRem, bottom = 1.cssRem)
            .displayIfAtLeast(Breakpoint.SM)
    }

//    val modifierLgMd = Modifier
//        .padding(left = 8.cssRem, top = 1.cssRem, bottom = 0.5.cssRem)
//
//    Breakpoint.LG { modifierLgMd }
//    Breakpoint.MD { modifierLgMd }
}

val LastFmBoxStyle = CssStyle {
    base {
        Modifier
            .fillMaxHeight()
            .width(40.percent)
            .backgroundColor(SitePalette.primaryColor)
            .color(SitePalette.accentColorOne)
            .fontFamily("Space", "Mono", "monospace")
    }

    Breakpoint.MD { Modifier.fontSize(1.cssRem) }
    Breakpoint.SM {
        Modifier
            .fontSize(1.cssRem)
            .fillMaxWidth()
            .borderBottom(5.cssRem, LineStyle.Dotted, SitePalette.accentColorOne)
    }
}

@Composable
private fun LastFmThing() {
    Row(LastFmBoxStyle.toModifier(), verticalAlignment = Alignment.CenterVertically) {
        var isPlayingSong by remember { mutableStateOf(false) }
        var songName by remember { mutableStateOf("Loading song...") }
        var artistName by remember { mutableStateOf("Loading artist...") }
        var imageLink by remember { mutableStateOf("") }

        val url =
            "https://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=GChen3843&api_key=d7b26ab026668c5044cc4610d092bcd2&format=json";

        val client = HttpClient(Js)
//        {
//            install(ContentNegotiation) {
//                json()
//            }
//        }

        val scope = rememberCoroutineScope()

        LaunchedEffect(songName) {
            delay(10.seconds)

            val playingSongFetched: Boolean = false
            val artistNameFetched: String = artistName
            val songNameFetched: String = songName
            val imageLinkFetched: String = imageLink

            val response = HttpClient(Js).get(url)
            if (response.status.isSuccess()) {
                with(
                    Json.parseToJsonElement(response.bodyAsText()).jsonObject
                        .getValue("recenttracks").jsonObject
                        .getValue("track").jsonArray
                        .getOrNull(0)?.jsonObject
                ) {
                    if (this != null) {
                        // makes sure that they're all ready before being changed
                        val _playingSong: Boolean =
                            this["@attr"]!!.jsonObject["nowplaying"]!!.jsonPrimitive.content == "true"
                        val _artistName: String =
                            this["artistName"]!!.jsonObject["#text"]!!.jsonPrimitive.content
                        val _songName: String = this["songName"]!!.jsonPrimitive.content
                        val _imageLink: String =
                            this["image"]?.jsonArray[2]?.jsonObject["#text"]?.jsonPrimitive?.content ?: ""
                    }
                }

                isPlayingSong = playingSongFetched
                artistName = artistNameFetched
                songName = artistNameFetched
                imageLink = imageLinkFetched

            }
        }

        Image(src = imageLink)
        Column(verticalArrangement = Arrangement.Center) {
            SpanText(if (isPlayingSong) "Now listening to:" else "Last listened to", Modifier.fontSize(1.5.cssRem))
            SpanText(songName, Modifier.fontWeight(700))
        }
    }
}

@Composable
fun Header() {
    Header {
        Row(
            modifier = Modifier.fillMaxWidth().background(SitePalette.secondaryColorOne),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            H1(HeaderTitleStyle.toAttrs()) { Text("My Page") }
            Aside { LastFmThing() }
        }
    }
}