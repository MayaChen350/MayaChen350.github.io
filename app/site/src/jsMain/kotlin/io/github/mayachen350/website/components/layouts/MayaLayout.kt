package io.github.mayachen350.website.components.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.layout.Layout
import io.github.mayachen350.website.components.sections.Header

@Composable
@Layout
fun MayaLayout(content: @Composable () -> Unit) {

    content()
}