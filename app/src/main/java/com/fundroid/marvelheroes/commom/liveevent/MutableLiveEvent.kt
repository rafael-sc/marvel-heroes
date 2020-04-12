package com.fundroid.marvelheroes.commom.liveevent

import androidx.lifecycle.MutableLiveData

class MutableLiveEvent : LiveEvent() {
    override val liveData = MutableLiveData<Event<Unit>>()

    fun emit() {
        liveData.postValue(Event(Unit))
    }

    fun isEmitted(): Boolean = liveData.value != null
}
