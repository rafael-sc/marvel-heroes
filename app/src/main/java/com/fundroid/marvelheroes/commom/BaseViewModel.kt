package com.fundroid.marvelheroes.commom

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel() {
    private var viewModelJob = Job()
    val viewModelScope = CoroutineScope(Dispatchers.IO + viewModelJob)
}
