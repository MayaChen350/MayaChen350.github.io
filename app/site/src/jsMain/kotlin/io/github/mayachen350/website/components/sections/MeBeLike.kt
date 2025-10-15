package io.github.mayachen350.website.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.AlignSelf
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.alignContent
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.alignSelf
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.border
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
import com.varabyte.kobweb.silk.theme.colors.palette.Palette
import io.github.mayachen350.data.horribleIdeas
import io.github.mayachen350.website.Font
import io.github.mayachen350.website.SitePalette
import io.github.mayachen350.website.fontFamily
import org.jetbrains.compose.web.css.AlignContent
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Col
import org.jetbrains.compose.web.dom.Div
import kotlin.random.Random

val pfpStyle = CssStyle {
    Breakpoint.MD {
        Modifier
            .alignSelf(AlignSelf.Start)
            .padding(left = .5.cssRem, top = 2.5.cssRem)
            .flexGrow(1)
//            .background {
//                val gradient = linearGradient(angle = 300.deg) {
//                    add(Color.rgb(0), 50.percent)
//
//                }
//                image(gradient)
//            }
    }


    until(Breakpoint.MD) {
        Modifier.alignSelf(AlignSelf.Center)
    }
}

val firstSectionStyle = CssStyle {
    base {
        Modifier.display(DisplayStyle.Flex).fillMaxWidth()
    }

    Breakpoint.MD {
        Modifier
            .flexDirection(FlexDirection.Row)
    }

    until(Breakpoint.SM) {
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
                .size(25.cssRem)
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

private class WeekReferences {
    val mon: String = getRandomThing();
    val tue: String = getRandomThing()
    val wed: String = getRandomThing()
    val thu: String = getRandomThing()
    val fri: String = getRandomThing()
    val sat: String = getRandomThing()
    val sun: String = getRandomThing()

    private fun getRandomThing(): String = horribleIdeas[Random.nextInt(until = horribleIdeas.size)]
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
        with(WeekReferences()) {
            mon = this.mon
            tue = this.tue
            wed = this.wed
            thu = this.thu
            fri = this.fri
            sat = this.sat
            sun = this.sun
        }
    }

    Column(
        Modifier
            .flexGrow(3)
            .padding(all = 3.cssRem)
            .fontSize(2.cssRem)
            .color(Colors.GhostWhite)
            .fontFamily(Font.LUMANOSIMO)
            .border {
                color(SitePalette.primaryColor)
                style(top = LineStyle.None, LineStyle.Solid, bottom = LineStyle.Solid)
                width(.2.cssRem)
            }
            .background {
                color(Color.rgb(0x191611))
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        SpanText("Week planning")
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