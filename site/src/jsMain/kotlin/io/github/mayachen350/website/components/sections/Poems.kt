package io.github.mayachen350.website.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Column
import io.github.mayachen350.data.Poems
import io.github.mayachen350.data.fetchPoems
import io.github.mayachen350.website.components.widgets.IconButton
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.dom.P
import org.w3c.dom.Text

@Composable
fun Poems() {
    var poems: Poems by remember { mutableStateOf(listOf(listOf(""))) }
    var poemIndex by remember { mutableStateOf(0) }

    var buttonStates by remember { mutableStateOf(false to true) }

    LaunchedEffect(Unit) {
        poems = fetchPoems()
    }

    LaunchedEffect(poemIndex) {
        buttonStates = when (poemIndex) {
            0 -> false to true
            poems.size -> true to false
            else -> true to true
        }
    }

    val scope = rememberCoroutineScope()

    Column {
        poems[poemIndex].forEach {
            P {
                Text(it)
            }
        }
        Text(poems[poemIndex][1])

        IconButton(
            onClick = {
                scope.launch {
                    poemIndex++
                }
            }
        )
        {
            Text("->")
        }
    }
}