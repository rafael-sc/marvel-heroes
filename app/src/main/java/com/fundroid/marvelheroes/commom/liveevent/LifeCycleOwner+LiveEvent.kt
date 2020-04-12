package com.fundroid.marvelheroes.commom.liveevent


import androidx.lifecycle.LifecycleOwner


fun <T> LifecycleOwner.consume(liveEventData: LiveEventData<T>, onEvent: (T) -> Unit) {
    liveEventData.consume(this) {
        onEvent(it)
    }
}

fun LifecycleOwner.consume(liveEvent: LiveEvent, onEvent: () -> Unit) {
    liveEvent.consume(this, onEvent)
}
