package io.github.mayachen350.utils

fun String.nullIfBlank(): String? = this.ifBlank { null }