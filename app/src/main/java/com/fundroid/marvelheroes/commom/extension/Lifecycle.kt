package com.fundroid.marvelheroes.commom.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, onChange: (T) -> Unit) {
    liveData.observe(this, Observer {
        it ?: return@Observer
        onChange(it)
    })
}

fun LifecycleOwner.observeEvent(liveData: LiveData<Void>, onChange: () -> Unit) {
    liveData.observe(this, Observer {
        onChange()
    })
}