package com.pradip.flytebaseasignment.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pradip.flytebaseasignment.business.MADSCalculator
import com.pradip.flytebaseasignment.data.Repository
import com.pradip.flytebaseasignment.extentions.setDefaultValue
import com.pradip.flytebaseasignment.ui.home.MainFragment

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _error = MutableLiveData<String>().setDefaultValue("")
    val error: LiveData<String> = _error
    val etValue = ObservableField<String>("")
    val result = ObservableField<String>("")
    fun sendValue(key: String, value: String) {
        repository.saveValue(key, value)
    }

    fun setEtValue() {
        etValue.set(result.get())
    }

    fun calculate() {
        if (isValidInput(etValue.get())) {
            try {
                val calc = MADSCalculator.evaluate(etValue.get()!!).toString()
                result.set(calc)

                sendValue(etValue.get()!!, result.get()!!)
            } catch (e: Exception) {
                _error.postValue(MainFragment.ERROR)
            }

        } else {
            _error.postValue(MainFragment.VALID_IP)
        }
    }

    private fun isValidInput(text: String?): Boolean {
        return if (!text.isNullOrEmpty()) {
            val countOp = text.count { MainFragment.OPERATORS.contains(it) }
            val countSp = text.count { it == ' ' }
            val countDig = text.count { it.isDigit() }
            countOp * 2 == countSp && countDig > 1
        } else
            false
    }
}