package io.github.mayachen350.website.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.until
import io.github.mayachen350.data.LfmResponse
import io.github.mayachen350.utils.nullIfBlank
import io.github.mayachen350.website.SitePalette
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.js.Js
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.Url
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.xml.xml
import kotlinx.browser.window
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.await
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.hsla
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Aside
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.asList
import org.w3c.dom.get
import org.w3c.dom.parsing.DOMParser
import org.w3c.dom.url.URL
import kotlin.js.iterator
import kotlin.time.Duration
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
            .fillMaxWidth()
            .background(Color.rgb(0x090909))
//            .backgroundImage(linearGradient(Color.rgb(0x8396e1), SitePalette.secondaryColorOne, Color.rgb(0x22)))
            .color(Color.rgb(0xFFFFFF))
//            .fontFamily("Space", "Mono", "monospace")
            .borderBottom(0.25.cssRem, LineStyle.Ridge, SitePalette.primaryColor)
    }

    until(Breakpoint.MD) { Modifier.fontSize(1.cssRem) }

    until(Breakpoint.SM) {
        Modifier
            .fontSize(1.cssRem)
            .fillMaxWidth()

    }
}

/**
 * Choices are:
 * - small
 * - medium
 * - large
 * - extralarge
 * */
private const val albumImageSize: String = "large"

private const val errorAlbumLink: String = ""

private val delayLastFmStatusRefresh: Duration = 30.seconds

@Composable
private fun LastFmThing() {
    Row(LastFmBoxStyle.toModifier(), verticalAlignment = Alignment.CenterVertically) {
        var isPlayingSong by remember { mutableStateOf(false) }
        var songName by remember { mutableStateOf("Loading song...") }
        var artistName by remember { mutableStateOf("Loading artist...") }
        var imageLink by remember { mutableStateOf("") }

        val scope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            scope.launch {
                while (true) {
                    val data = fetchLastFmData()
                    if (data != null) {
                        withContext(Dispatchers.Main) {
                            isPlayingSong = data.isListening
                            songName = data.trackName
                            artistName = data.artistName
                            imageLink = data.albumLink
                        }
                    }
                    delay(delayLastFmStatusRefresh)
                }
            }
        }

        Image(src = imageLink, Modifier.maxHeight(100.percent))
        Column(
            modifier = Modifier.fillMaxSize().textAlign(TextAlign.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpanText(if (isPlayingSong) "Now listening to:" else "Last listened to", Modifier
                .fontSize(1.5.cssRem)
                .align(Alignment.Start))
            SpanText(songName, Modifier
                .color(SitePalette.primaryColor)
                .fontWeight(700)
                .fontSize(5.cssRem)
                .letterSpacing(2.cssRem))
            SpanText("By $artistName", Modifier
                .fontWeight(500)
                .fontSize(2.cssRem)
                .align(Alignment.End))
        }
    }
}

@Composable
fun Header() {
    Header {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.9.cssRem)
                .background(SitePalette.secondaryColorOne),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Aside(Modifier.fillMaxHeight().width(100.percent).toAttrs()) {
                LastFmThing()
            }
        }
    }
}

private const val url =
    "https://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=GChen3843&api_key=d7b26ab026668c5044cc4610d092bcd2&format=xml&limit=1";

private suspend fun fetchLastFmData(): LastFmNowListeningInfo? = coroutineScope {
    val isPlayingSongFetched: Boolean
    val artistNameFetched: String
    val trackNameFetched: String
    val imageLinkFetched: String
    console.log("Fetching song from url")

    val responseTrackData = DOMParser().parseFromString(
        window.fetch(url).await()
            .also {
                if (!it.ok) return@coroutineScope null.also {
                    console.log("response was not ok")
                }
            }.text().await(),
        "application/xml"
    ).also { console.log(it) }.documentElement

    if (responseTrackData === null) return@coroutineScope responseTrackData.also { console.log("response was null") }

    isPlayingSongFetched = responseTrackData.getElementsByTagName("track")[0]
        ?.getAttribute("nowplaying") == "true"

    artistNameFetched = responseTrackData.getElementsByTagName("artist")[0]
        ?.textContent?.nullIfBlank() ?: "Unknown artist"

    trackNameFetched = responseTrackData.getElementsByTagName("track")[0]
        ?.getElementsByTagName("name")[0]?.textContent?.nullIfBlank() ?: "Unknown track"

    imageLinkFetched = responseTrackData.getElementsByTagName("image")
        .asList().firstOrNull() { it.getAttribute("size") == albumImageSize }
        ?.textContent?.nullIfBlank() ?: errorAlbumLink

    LastFmNowListeningInfo(isPlayingSongFetched, trackNameFetched, artistNameFetched, imageLinkFetched)
        .also { console.log("Final result: $it") }
}


data class LastFmNowListeningInfo(
    val isListening: Boolean,
    val trackName: String,
    val artistName: String,
    val albumLink: String
)