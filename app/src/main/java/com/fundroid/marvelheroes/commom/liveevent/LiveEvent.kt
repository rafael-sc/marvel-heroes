package com.arctouch.codechallenge.commom.liveevent

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


abstract class LiveEvent {
    protected abstract val liveData: LiveData<Event<Unit>>

    internal fun consume(lifecycleOwner: LifecycleOwner, onConsume: () -> Unit) {
        liveData.observe(lifecycleOwner, Observer { event ->
            event?.handleEvent {
                onConsume()
                true
            }
        })
    }
}
