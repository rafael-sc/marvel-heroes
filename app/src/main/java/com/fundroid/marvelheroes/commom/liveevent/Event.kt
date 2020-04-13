package com.fundroid.marvelheroes.commom.liveevent

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class Event<out T>(private val content: T) {
    var hasBeenConsumed = false
        private set // Allow external read but not write

    /**
     * Handles the event and consumes it if lambda call returns true
     */
    fun handleEvent(call: (T) -> Boolean) {
        if (!hasBeenConsumed) {
            if (call(content))
                hasBeenConsumed = true
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}
