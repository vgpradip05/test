package com.pradip.flytebaseasignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pradip.flytebaseasignment.data.Repository
import com.pradip.flytebaseasignment.extentions.setDefaultValue

class ListViewModel(private val repository: Repository) :ViewModel(){
    var isLoading = MutableLiveData<Boolean>().setDefaultValue(false)
    private val _list = MutableLiveData<List<String>>()
    val list:  LiveData<List<String>> = _list

    fun getDataFromFirebase(){
        isLoading.postValue(true)
        repository.getValues(object :Repository.ICallback<List<String>,String>{
            override fun onSuccess(response: List<String>) {
                _list.postValue(response)
                isLoading.postValue(false)
            }

            override fun onFailure(failure: String) {
                isLoading.postValue(false)
            }
        })
    }

}

