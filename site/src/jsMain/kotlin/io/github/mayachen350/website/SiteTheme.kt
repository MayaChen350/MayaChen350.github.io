package io.github.mayachen350.website

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.core.init.InitKobweb
import com.varabyte.kobweb.core.init.InitKobwebContext
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.Palette
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.percent
import kotlin.reflect.KProperty


private var primaryColorHue by mutableStateOf(0f);
private var secondaryColorOneHue by mutableStateOf(299);
private var secondaryColorTwoHue by mutableStateOf(47f);
private var accentColorOneHue by mutableStateOf(340f);
private var accentColorTwoHue by mutableStateOf(253f);


object SitePalette {
    var primaryColor: Color = Color.rgb(0xEFC256)
    var secondaryColorOne: Color = Color.hsl(secondaryColorOneHue.deg, 57.0f.percent, 55.30f.percent)
    var secondaryColorTwo: Color = Color.hsl(secondaryColorTwoHue.deg, 100.percent, 52.70f.percent)
    var accentColorOne: Color = Color.hsl(accentColorOneHue.deg, 100.percent, 84.10f.percent)
    var accentColorTwo: Color = Color.hsl(accentColorTwoHue.deg, 45.50f.percent, 82.70f.percent)
}

object Fonts {
    const val QUINTESSENTIAL = "Quintessential"
}

// TODO: Make this into const strings if lists end up useless
enum class Font(val value: MutableList<String>) {
    SPACE_MONO(mutableListOf("Space Mono")),
    NUNITO_SANS(mutableListOf("Nunito Sans")),
    SACRAMENTO(mutableListOf("Sacramento")),
    LUMANOSIMO(mutableListOf("Lumanosimo")),
    QUINTESSENTIAL(mutableListOf("Quintessential"));

    operator fun plus(element: String) = value.apply { add(element) }
}

fun Modifier.fontFamily(font: Font): Modifier = this.fontFamily(font.value)