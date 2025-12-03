package io.github.mayachen350.data

import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.ExperimentalSerializationApi

typealias Poems = Array<Array<String>>

// TODO: Clean this
@OptIn(ExperimentalSerializationApi::class)
suspend fun fetchPoems(): Poems = run {
    val response = window.fetch("/data/selected_poems.json").await()
    response.json().await().asDynamic()
}
