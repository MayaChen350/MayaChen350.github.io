package io.github.mayachen350.website.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.AlignSelf
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.until
import io.github.mayachen350.data.horribleIdeas
import io.github.mayachen350.website.Fonts
import io.github.mayachen350.website.SitePalette
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

val pfpStyle = CssStyle {
    Breakpoint.MD {
        Modifier
            .alignSelf(AlignSelf.Start)
            .flexGrow(1)
    }


    until(Breakpoint.MD) {
        Modifier
            .alignSelf(AlignSelf.Center)
            .padding(top = 1.5.cssRem)
    }
}

val firstSectionStyle = CssStyle {
    base {
        Modifier.display(DisplayStyle.Flex).fillMaxWidth()
    }

    Breakpoint.LG {
        Modifier
            .flexDirection(FlexDirection.Row)
    }

    until(Breakpoint.MD) {
        Modifier.flexDirection(FlexDirection.Column)
    }
}

@Composable
private fun Pfp(pfpLink: String, text: String) {
    // TODO: Make it change
    Column(
        pfpStyle.toModifier(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            pfpLink,
            Modifier
                .borderRadius(50.percent)
                .maxSize(25.cssRem)
        )
        SpanText(
            text,
            Modifier
                .fontSize(2.cssRem)
                .alignSelf(AlignSelf.Center)
                .textAlign(TextAlign.Center)
                .padding(top = 1.5.cssRem)
                .color(SitePalette.primaryColor)
        )
    }
}

val refCadre = CssStyle {
    base {
        Modifier
            .flexGrow(3)
            .padding(all = 3.cssRem)
            .fontSize(2.cssRem)
            .color(Colors.GhostWhite)
            .fontFamily(Fonts.LUMANOSIMO)
            .background {
                color(Color.rgb(0x191611))
            }
    }

    Breakpoint.MD {
        Modifier.border {
            color(SitePalette.primaryColor)
            style(top = LineStyle.None, LineStyle.Solid, bottom = LineStyle.Solid)
            width(.2.cssRem)
        }
    }

    until(Breakpoint.MD) {
        Modifier.border {
            color(SitePalette.primaryColor)
            style(top = LineStyle.Dotted, LineStyle.None, bottom = LineStyle.Dotted)
            width(.2.cssRem)
        }
            .fontSize(1.5.cssRem)
    }
}

@Composable
private fun BunchOfReferences() {
    var mon by remember { mutableStateOf("") }
    var tue by remember { mutableStateOf("") }
    var wed by remember { mutableStateOf("") }
    var thu by remember { mutableStateOf("") }
    var fri by remember { mutableStateOf("") }
    var sat by remember { mutableStateOf("") }
    var sun by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        mon = horribleIdeas[0]
        tue = horribleIdeas[2]
        wed = horribleIdeas[3]
        thu = horribleIdeas[4]
        fri = horribleIdeas[5]
        sat = horribleIdeas[6]
        sun = horribleIdeas[7]
    }

    Column(
        refCadre.toModifier(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        SpanText("Week planning", Modifier.fontSize(2.5.cssRem).fontWeight(700))
        SpanText("MON: $mon")
        SpanText("TUE: $tue")
        SpanText("WED: $wed")
        SpanText("THU: $thu")
        SpanText("FRI: $fri")
        SpanText("SAT: $sat")
        SpanText("SUN: $sun")
    }
}

@Composable
fun MeBeLike() {
    Div(firstSectionStyle.toAttrs()) {
        Pfp(
            "https://cdn.discordapp.com/avatars/491212588768821281/49242a69cf9e0f614ce44c259b8749a1.webp?size=256",
            "Real (real) name: Antoinette C."
        )
        BunchOfReferences()
    }
}