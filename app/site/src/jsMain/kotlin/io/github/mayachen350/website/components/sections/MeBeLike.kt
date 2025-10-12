package io.github.mayachen350.website.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.AlignSelf
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.alignContent
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.alignSelf
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexDirection
import com.varabyte.kobweb.compose.ui.modifiers.flexGrow
import com.varabyte.kobweb.compose.ui.modifiers.flexShrink
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.until
import io.github.mayachen350.website.SitePalette
import org.jetbrains.compose.web.css.AlignContent
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Div

val pfpStyle = CssStyle {
    Breakpoint.MD {
        Modifier
            .alignSelf(AlignSelf.Start)
            .padding(left = .5.cssRem, top = 2.5.cssRem)
            .flexGrow(1)
    }


    until(Breakpoint.MD) {
        Modifier.alignSelf(AlignSelf.Center)
    }
}

val firstSectionStyle = CssStyle {
    base {
        Modifier.display(DisplayStyle.Flex)
    }

    Breakpoint.MD {
        Modifier
            .fillMaxWidth()
            .flexDirection(FlexDirection.Row)
    }

    until(Breakpoint.MD) {
        Modifier.flexDirection(FlexDirection.Column)
    }
}

@Composable
private fun Pfp() {
    Column(pfpStyle.toModifier(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            "https://cdn.discordapp.com/avatars/491212588768821281/49242a69cf9e0f614ce44c259b8749a1.webp?size=256",
            Modifier
                .borderRadius(50.percent)
                .size(25.cssRem)
        )
        SpanText(
            "Real (real) name: Antoinette C.",
            Modifier
                .fontSize(2.cssRem)
                .alignSelf(AlignSelf.Center)
                .textAlign(TextAlign.Center)
                .padding(top = 1.5.cssRem)
                .color(SitePalette.primaryColor)
        )
    }
}

@Composable
private fun BunchOfReferences() {
    Column(Modifier.flexGrow(4).fontSize(2.cssRem).color(Colors.GhostWhite),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start) {
        SpanText("Week planning")
        SpanText("MON:")
        SpanText("TUE:")
        SpanText("WED:")
        SpanText("THU:")
        SpanText("FRI:")
        SpanText("SAT:")
        SpanText("SUN:")
    }
}

@Composable
fun MeBeLike() {
    Div(firstSectionStyle.toAttrs()) {
        Pfp()
        BunchOfReferences()
    }
}