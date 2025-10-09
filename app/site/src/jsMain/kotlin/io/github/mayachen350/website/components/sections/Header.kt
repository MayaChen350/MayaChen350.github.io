package io.github.mayachen350.website.components.sections

import androidx.compose.runtime.Composable
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
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import io.github.mayachen350.website.SitePalette
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Aside
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.Text

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

    val modifierLgMd = Modifier
        .padding(left = 8.cssRem, top = 1.cssRem, bottom = 0.5.cssRem)

    Breakpoint.LG { modifierLgMd }
    Breakpoint.MD { modifierLgMd }
}

val LastFmBoxStyle = CssStyle {
    base {
        Modifier
            .backgroundColor(SitePalette.primaryColor)
            .width(40.percent)
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
    Column(LastFmBoxStyle.toModifier(), verticalArrangement = Arrangement.Center) {
        Text("Last FM")
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