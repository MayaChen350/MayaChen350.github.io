package io.github.mayachen350.website.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorPalettes
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.Col
import org.jetbrains.compose.web.dom.Text

@Composable
private fun LastFmThing() {
    Column(Modifier.backgroundColor(ColorPalettes.Red._100)) {
        Text("Last FM")
    }
}

@Composable
fun Header() {
    Row(Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
            SpanText("My Page", Modifier.fontFamily("Sacramento").fontSize(5.cssRem))
        LastFmThing()
    }
}