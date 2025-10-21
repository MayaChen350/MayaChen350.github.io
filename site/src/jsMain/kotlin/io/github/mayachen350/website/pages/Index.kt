package io.github.mayachen350.website.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.BackgroundAttachment
import com.varabyte.kobweb.compose.css.BackgroundRepeat
import com.varabyte.kobweb.compose.css.CSSPosition
import com.varabyte.kobweb.compose.css.ColorInterpolationMethod
import com.varabyte.kobweb.compose.css.functions.Gradient
import com.varabyte.kobweb.compose.css.functions.RadialGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.css.functions.radialGradient
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.style.*
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import io.github.mayachen350.website.Fonts
import io.github.mayachen350.website.SitePalette
import io.github.mayachen350.website.components.layouts.PageLayoutData
import io.github.mayachen350.website.components.sections.Header
import io.github.mayachen350.website.components.sections.MeBeLike
import io.github.mayachen350.website.components.sections.interestBox
import io.github.mayachen350.website.components.sections.wallOfStuff
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

// Container that has a tagline and grid on desktop, and just the tagline on mobile
val HeroContainerStyle = CssStyle {
    base { Modifier.fillMaxWidth().gap(2.cssRem) }
    Breakpoint.MD { Modifier.margin { top(20.vh) } }
}

// A demo grid that appears on the homepage because it looks good
val HomeGridStyle = CssStyle.base {
    Modifier
        .gap(0.5.cssRem)
        .width(70.cssRem)
        .height(18.cssRem)
}


fun Modifier.indexCssBackground(): Modifier =
    this
        .background(Colors.Black)
        .opacity(0.8)
        .styleModifier {
            property(
                "background",
                "radial-gradient(circle, transparent 20%, #000000 20%, #000000 80%, transparent 80%, transparent), radial-gradient(circle, transparent 20%, #000000 20%, #000000 80%, transparent 80%, transparent) 25px 25px, linear-gradient(#EFC256 2px, transparent 2px) 0 -1px, linear-gradient(90deg, #EFC256 2px, #000000 2px) -1px 0"
            )
            property("background-size", "50px 50px, 50px 50px, 25px 25px, 25px 25px")
        }


@InitSilk
fun initIndexSilk(ctx: InitSilkContext) {
    ctx.stylesheet.registerStyle("html") {
        base {
            Modifier.background {
                color(Color.rgb(0x090909))
            }
        }

    }

    ctx.stylesheet.registerStyle("body") {
        val backgroundMainColor = Color.rgb(0x191611)

        val backgroundGradient: Gradient =
            radialGradient(shape = RadialGradient.Shape.Ellipse, position = CSSPosition.TopLeft) {
                add(backgroundMainColor)
                add(Color.rgb(0x1C1919))
            }

        base {
            Modifier
                .fontSize(1.8.cssRem)
                .color(SitePalette.primaryColor)

                .fontFamily(Fonts.QUINTESSENTIAL, "sans-serif")

        }

        Breakpoint.LG {
            Modifier.fontSize(1.7.cssRem)
        }

        Breakpoint.MD {
            Modifier.fontSize(1.65.cssRem)
        }

        Breakpoint.SM {
            Modifier.fontSize(1.6.cssRem)
        }
    }
}


@InitRoute
fun initHomePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Home"))
}

val cursedThingStyle = CssStyle {
    fun Modifier.theBackground(stopPoint: CSSSizeValue<CSSUnit.percent>) = this.background {


        val gradient = linearGradient(angle = 315.deg, ColorInterpolationMethod.Srgb) {
            add(Color.rgb(0xE6B434))
            add(Color.rgb(0x090909), stopPoint)

        }

        this.repeat(BackgroundRepeat.NoRepeat)
        this.attachment(BackgroundAttachment.Fixed)

        image(gradient)
    }


    base {
        Modifier
            .position(Position.Fixed)
            .fillMaxHeight()
            .right(0.cssRem)
            .zIndex(-1)
    }

    Breakpoint.LG {
        Modifier
            .width(70.percent)
            .theBackground(40.percent)
    }

    between(Breakpoint.SM,Breakpoint.LG) {
        Modifier.fillMaxWidth().theBackground(60.percent)
    }

    until(Breakpoint.SM) {
        Modifier.fillMaxWidth().theBackground(40.percent)
    }
}

@Composable
fun CursedThingsDoneThingsSomething() {
    Div(
        cursedThingStyle.toAttrs { }
    ) {

    }
}

@Page
@Layout(".components.layouts.MayaLayout")
@Composable
fun HomePage() {

    Column(Modifier.fillMaxSize()) {
        CursedThingsDoneThingsSomething()
        Header()
        MeBeLike()

        wallOfStuff()
    }
}

fun PeopleWhoKnowsWhatTheyAreDoingHomePage() {
//    Row(HeroContainerStyle.toModifier()) {
//        Box {
//            val sitePalette = ColorMode.current.toSitePalette()
//
//            Column(Modifier.gap(2.cssRem)) {
//                Div(HeadlineTextStyle.toAttrs()) {
//                    SpanText(
//                        "Use this template as your starting point for ", Modifier.color(
//                            when (ColorMode.current) {
//                                ColorMode.LIGHT -> Colors.Black
//                                ColorMode.DARK -> Colors.White
//                            }
//                        )
//                    )
//                    SpanText(
//                        "Kobweb",
//                        Modifier
//                            .color(sitePalette.brand.accent)
//                            // Use a shadow so this light-colored word is more visible in light mode
//                            .textShadow(0.px, 0.px, blurRadius = 0.5.cssRem, color = Colors.Gray)
//                    )
//                }
//
//                Div(SubheadlineTextStyle.toAttrs()) {
//                    SpanText("You can read the ")
//                    Link("/about", "About")
//                    SpanText(" page for more information.")
//                }
//
//                val ctx = rememberPageContext()
//                Button(onClick = {
//                    // Change this click handler with your call-to-action behavior
//                    // here. Link to an order page? Open a calendar UI? Play a movie?
//                    // Up to you!
//                    ctx.router.tryRoutingTo("/about")
//                }, colorPalette = ColorPalettes.Blue) {
//                    Text("This could be your CTA")
//                }
//            }
//        }
//
//        Div(
//            HomeGridStyle
//            .toModifier()
//            .displayIfAtLeast(Breakpoint.MD)
//            .grid {
//                rows { repeat(3) { size(1.fr) } }
//                columns { repeat(5) { size(1.fr) } }
//            }
//            .toAttrs()
//        ) {
//            val sitePalette = ColorMode.current.toSitePalette()
//            GridCell(sitePalette.brand.primary, 1, 1, 2, 2)
//            GridCell(ColorPalettes.Monochrome._600, 1, 3)
//            GridCell(ColorPalettes.Monochrome._100, 1, 4, width = 2)
//            GridCell(sitePalette.brand.accent, 2, 3, width = 2)
//            GridCell(ColorPalettes.Monochrome._300, 2, 5)
//            GridCell(ColorPalettes.Monochrome._800, 3, 1, width = 5)
//        }
//    }
}
