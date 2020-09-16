package com.constevents.utils

/**
 * Default exception
 */
class DefaultException(
    val code: String = "",
    override val message: String = "Unexpected error"
) : Exception()
