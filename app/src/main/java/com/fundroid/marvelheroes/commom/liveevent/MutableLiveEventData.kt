package com.arctouch.codechallenge.commom.liveevent

import androidx.lifecycle.MutableLiveData

class MutableLiveEventData<T> : LiveEventData<T>() {
    override val liveData = MutableLiveData<Event<T>>()

    fun emit(data: T) {
        liveData.postValue(Event(data))
    }

    fun peek(): T? = liveData.value?.peekContent()
}
