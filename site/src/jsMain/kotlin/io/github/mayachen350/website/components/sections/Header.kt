package io.github.mayachen350.website.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.TextAlign
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
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.until
import io.github.mayachen350.website.utils.nullIfBlank
import io.github.mayachen350.website.SitePalette
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.dom.addClass
import kotlinx.dom.removeClass
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Aside
import org.jetbrains.compose.web.dom.Header
import org.w3c.dom.asList
import org.w3c.dom.get
import org.w3c.dom.parsing.DOMParser
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
            .fillMaxSize()
//            .background() {
//                val gradient = linearGradient(angle = 315.deg) {
//                    add(Color.rgb(0xE6B434))
//                    add(Color.rgb(0x090909), 65.percent)
//                }
//
//                image(gradient)
//            }
//            .backgroundImage(linearGradient(Color.rgb(0x8396e1), SitePalette.secondaryColorOne, Color.rgb(0x22)))
            .color(Color.rgb(0xFFFFFF))
//            .fontFamily("Space", "Mono", "monospace")
            .borderBottom(0.25.cssRem, LineStyle.Ridge, SitePalette.primaryColor)
    }

    until(Breakpoint.MD) {
        Modifier.height(13.5.cssRem).fontSize(2.cssRem)
    }

    until(Breakpoint.SM) {
        Modifier
            .height(10.cssRem)
            .fontSize(1.5.cssRem)
            .fillMaxWidth()

    }
}

val LastFmSongTitleStyle = CssStyle {
    base {
        Modifier
            .color(SitePalette.primaryColor)
            .fontWeight(700)
            .fontSize(5.cssRem)
            .letterSpacing(0.85.cssRem)
            .fontSize(5.cssRem)
    }

    until(Breakpoint.MD) { Modifier.fontSize(4.cssRem) }

    until(Breakpoint.SM) {
        Modifier
            .fontSize(2.5.cssRem)
            .fillMaxWidth()

    }
}

val LastFmSongTitleSmallerStyle = CssStyle {
    until(Breakpoint.MD) {
        Modifier
            .fontSize(2.cssRem)
            .letterSpacing(0.cssRem)
    }
}

val LastFmSongTitleEvenSmallerStyle = CssStyle {
    Breakpoint.LG {
        Modifier
            .letterSpacing(0.cssRem)
    }

    until(Breakpoint.LG) {
        Modifier
            .fontSize(2.cssRem)
            .letterSpacing(0.cssRem)
    }

    until(Breakpoint.MD) {
        Modifier
            .fontSize(1.5.cssRem)
            .letterSpacing(0.cssRem)
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
                val lastFmSongTitleElement = document.getElementById("last-fm-song-title")
                while (true) {
                    val data = fetchLastFmData()
                    if (data != null) {
                        withContext(Dispatchers.Main) {
                            isPlayingSong = data.isListening
                            songName = data.trackName
                            artistName = data.artistName
                            imageLink = data.albumLink

                            if (songName.length > 25) {
                                lastFmSongTitleElement?.removeClass("last-fm-song-title-smaller")
                                lastFmSongTitleElement?.addClass("last-fm-song-title-even-smaller")
                            } else if (songName.length > 15) {
                                lastFmSongTitleElement?.addClass("last-fm-song-title-smaller")
                                lastFmSongTitleElement?.removeClass("last-fm-song-title-even-smaller")
                            } else {
                                lastFmSongTitleElement?.removeClass("last-fm-song-title-smaller")
                                lastFmSongTitleElement?.removeClass("last-fm-song-title-even-smaller")
                            }
                        }
                    }
                    delay(delayLastFmStatusRefresh)
                }
            }
        }

        val textPaddingSize = 2.5.cssRem

        Image(src = imageLink, Modifier.height(100.percent).maxHeight(15.9.cssRem))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .textAlign(TextAlign.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SpanText(
                    if (isPlayingSong) "Right now I'm listening to:" else "Last song I listened to:"
                )
                SpanText(
                    songName, LastFmSongTitleStyle.toModifier()
                        .then(LastFmSongTitleSmallerStyle.toModifier())
                        .then(LastFmSongTitleEvenSmallerStyle.toModifier())
                        .id("last-fm-song-title")
                )
                SpanText("By $artistName")
            }
        }
    }
}

@Composable
fun Header() {
    Header(Modifier.fillMaxWidth().toAttrs { }) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Aside(Modifier.fillMaxHeight().fillMaxWidth().toAttrs()) {
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