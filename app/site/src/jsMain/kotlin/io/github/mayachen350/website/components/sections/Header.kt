package io.github.mayachen350.website.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.CSSColor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorPalettes
import io.github.mayachen350.website.SitePalette
import io.github.mayachen350.website.toSitePalette
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.Col
import org.jetbrains.compose.web.dom.Text

val LastFmBoxStyle = CssStyle {
    base {
        Modifier.backgroundColor(com.varabyte.kobweb.compose.ui.graphics.Color.rgb(0x666666))
    }
}

@Composable
private fun LastFmThing() {
    Column(LastFmBoxStyle.toModifier()) {
        Text("Last FM")
    }
}

@Composable
fun Header() {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        SpanText("My Page", Modifier.fontFamily("Sacramento").fontSize(5.cssRem))
        LastFmThing()
    }
}