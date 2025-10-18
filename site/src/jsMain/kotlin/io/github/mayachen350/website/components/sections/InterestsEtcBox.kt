package io.github.mayachen350.website.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.ColorInterpolationMethod
import com.varabyte.kobweb.compose.css.FontSize
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.flexWrap
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.leftRight
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.outlineColor
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.theme.colors.palette.Palette
import io.github.mayachen350.data.interests
import io.github.mayachen350.website.Fonts
import io.github.mayachen350.website.SitePalette
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.percent
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
            .color(SitePalette.primaryColor)
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
            .background {
                color(Colors.Black)
            }
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
    Ul(wallOfStuffStyle.toAttrs()) {
        interests.forEach {
            interestBox(it)
        }
    }
}