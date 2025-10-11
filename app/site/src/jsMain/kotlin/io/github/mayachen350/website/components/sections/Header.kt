package io.github.mayachen350.website.components.sections

import androidx.compose.runtime.*
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.network.parseGetRequest
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.parser.Parser
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import io.github.mayachen350.utils.nullIfBlank
import io.github.mayachen350.website.SitePalette
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Aside
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.Text
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

/**
 * Choices are:
 * - small
 * - medium
 * - large
 * - extralarge
 * */
private const val albumImageSize: String = "medium"

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
                            songName = data.artistName
                            artistName = data.artistName
                            imageLink = data.albumLink
                        }
                    }
                    delay(delayLastFmStatusRefresh)
                }
            }
        }

        Image(src = imageLink)
        Column(verticalArrangement = Arrangement.Center) {
            SpanText(if (isPlayingSong) "Now listening to:" else "Last listened to", Modifier.fontSize(1.5.cssRem))
            SpanText(songName, Modifier.fontWeight(700))
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

    val responseTrackData: Element? =
        Ksoup.parseGetRequest(url = url, parser = Parser.xmlParser())
            .getElementsByTag("lfm").first()?.run { if (attribute("status")?.value == "ok") this else null }
            ?.getElementsByTag("recenttracks")?.first()
            ?.getElementsByTag("track")?.first()

    if (responseTrackData === null) return@coroutineScope responseTrackData

    isPlayingSongFetched = responseTrackData.attr("nowplaying") == "true"
    artistNameFetched = responseTrackData.getElementsByTag("artist").first()?.data()?.nullIfBlank() ?: "Unknown artist"
    trackNameFetched = responseTrackData.getElementsByTag("name").first()?.data()?.nullIfBlank() ?: "Unknown track"
    imageLinkFetched =
        responseTrackData.getElementsByTag("image").firstOrNull { it.attribute("size")?.value == albumImageSize }
            ?.data() ?: errorAlbumLink

    LastFmNowListeningInfo(isPlayingSongFetched, trackNameFetched, artistNameFetched, imageLinkFetched)
}

data class LastFmNowListeningInfo(
    val isListening: Boolean,
    val trackName: String,
    val artistName: String,
    val albumLink: String
)

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