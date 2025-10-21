package io.github.mayachen350.website.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.BackgroundSize
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toAttrs
import io.github.mayachen350.data.interests
import io.github.mayachen350.website.Fonts
import io.github.mayachen350.website.SitePalette
import org.jetbrains.compose.web.css.AlignSelf
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

@Composable
fun interestBox(text: String) {
    // TODO: Do something better and maybe blue
    Li(
        Modifier
//            .border {
//                style(LineStyle.Solid)
//                color(SitePalette.primaryColor.toRgb())
//            }
//            .background {
//                color(SitePalette.primaryColor.toRgb())
//            }
//            .borderRadius(15.percent)
//            .color(Color.rgb(0x1883FF))
            .fontFamily(Fonts.SPACE_MONO) // TODO: Change this goofy font
            .fontSize(2.cssRem)
            .fontWeight(FontWeight.Bold)
            .padding(all = 0.5.cssRem)
            .toAttrs()

    ) {
        Text(text.uppercase())
    }
}

val wallOfStuffStyle = CssStyle {
    base {
        Modifier
            .fillMaxSize()
//            .background {
//                color(Colors.Black)
//            }
            .color(SitePalette.primaryColor)
            .display(DisplayStyle.Flex)
            .flexWrap(FlexWrap.Wrap)
            .gap(3.cssRem)
            .padding {
                leftRight(5.cssRem)
            }
    }
}

@Composable
fun wallOfStuff() {
    SpanText(
        "Interests", Modifier
            .textAlign(TextAlign.Center)
            .color(SitePalette.primaryColor)
            .fontSize(3.cssRem)
            .padding {
                top(4.cssRem)
                bottom(4.cssRem)
            }
//            .background {
//                color(Colors.Black)
//            }
            .fillMaxWidth()
    )
    Ul(wallOfStuffStyle.toAttrs()) {
        interests.forEach {
            interestBox(it)
        }
    }
}