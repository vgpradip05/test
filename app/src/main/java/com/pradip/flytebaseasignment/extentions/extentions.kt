package com.pradip.flytebaseasignment.extentions

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.setDefaultValue(value: T): MutableLiveData<T> {
    this.value = value
    return this
}

@BindingAdapter("showIf")
fun bindShowIf(view: View, condition: Boolean) {
    view.visibility = if (condition) {
        View.VISIBLE
    } else {
        View.GONE
    }
}