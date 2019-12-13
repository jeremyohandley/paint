package com.ohandley.jeremy.paintapplication

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var color: MutableLiveData<Int> = MutableLiveData()
    var colors: ArrayList<Int>? = null
    private val defaultColor = Color.BLACK

    init {
        color.postValue(defaultColor)
    }

}