package com.fundroid.marvelheroes.commom.liveevent

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


abstract class LiveEventData<T> {
    protected abstract val liveData: LiveData<Event<T>>

    internal fun consume(lifecycleOwner: LifecycleOwner, onConsume: (T) -> Unit) {
        liveData.observe(lifecycleOwner, Observer { event ->
            event?.handleEvent { data ->
                onConsume(data)
                true
            }
        })
    }
}
