package io.github.mayachen350.website.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import org.jetbrains.compose.web.dom.Text

@Composable
fun Header() {
    Row(Modifier.fontFamily( "Sacramento")) {
        Text("My Page")
    }
}