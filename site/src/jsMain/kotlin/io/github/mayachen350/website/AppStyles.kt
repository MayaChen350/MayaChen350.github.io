package io.github.mayachen350.website

import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.forms.ButtonVars
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.addVariantBase
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.BreakpointSizes
import com.varabyte.kobweb.silk.style.layer.SilkLayer
import com.varabyte.kobweb.silk.style.layer.add
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import org.jetbrains.compose.web.css.*

@InitSilk
fun initSiteStyles(ctx: InitSilkContext) {
    ctx.stylesheet.cssLayers.add("custom_reset", after = SilkLayer.RESET)

    // This site does not need scrolling itself, but this is a good demonstration for how you might enable this in your
    // own site. Note that we only enable smooth scrolling unless the user has requested reduced motion, which is
    // considered a best practice.
    ctx.stylesheet.registerStyle("html") {
        cssRule(CSSMediaQuery.MediaFeature("prefers-reduced-motion", StylePropertyValue("no-preference"))) {
            Modifier.scrollBehavior(ScrollBehavior.Smooth)
        }
    }

    ctx.theme.palettes.light.background = Colors.Transparent
    ctx.theme.palettes.light.color = SitePalette.primaryColor

    // Silk dividers only extend 90% by default; we want full width dividers in our site
//    ctx.theme.modifyStyleBase(HorizontalDividerStyle) {
//        Modifier.fillMaxWidth()
//    }
}

@InitSilk
// TODO EVENTUALLY: Make better breakpoints
fun initBreakpoints(ctx: InitSilkContext) {
    ctx.theme.breakpoints = BreakpointSizes(
        // Original scss breakpoints
        sm = 600.px,
        md = 960.px,
        lg = 1280.px,
        xl = 1920.px,
        xxl = 5000.px,
    )
}

val HeadlineTextStyle = CssStyle.base {
    Modifier
        .fontSize(3.cssRem)
        .textAlign(TextAlign.Start)
        .lineHeight(1.2) //1.5x doesn't look as good on very large text
}

val SubheadlineTextStyle = CssStyle.base {
    Modifier
        .fontSize(1.cssRem)
        .textAlign(TextAlign.Start)
        .color(colorMode.toPalette().color.toRgb().copyf(alpha = 0.8f))
}

val CircleButtonVariant = ButtonStyle.addVariantBase {
    Modifier.padding(0.px).borderRadius(50.percent)
}

val UncoloredButtonVariant = ButtonStyle.addVariantBase {
    Modifier.setVariable(ButtonVars.BackgroundDefaultColor, Colors.Transparent)
}
