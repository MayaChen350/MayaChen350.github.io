package io.github.mayachen350.website.utils

fun String.nullIfBlank(): String? = this.ifBlank { null }