package com.example.support

class Consumable<T>(
    private val content: T
) {
    private var consumed = false

    fun consume(block: (T) -> Unit) {
        if (!consumed) {
            consumed = true
            block.invoke(content)
        }
    }
}

