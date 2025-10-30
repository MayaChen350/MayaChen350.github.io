package io.github.mayachen350.data

import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.decodeFromDynamic
import kotlin.js.Json

typealias Poems = List<List<String>>

// TODO: Clean this
@OptIn(ExperimentalSerializationApi::class)
suspend fun fetchPoems(): Poems = run {

    val response = window.fetch("/data/selected_poems.json").await()
    kotlinx.serialization.json.Json.decodeFromString<Poems>(response.json().await().asDynamic())
}
