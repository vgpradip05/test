package com.pradip.flytebaseasignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pradip.flytebaseasignment.data.Repository
import com.pradip.flytebaseasignment.extentions.setDefaultValue

class MainViewModel(private val repository: Repository) :ViewModel(){
    private val list = MutableLiveData<List<String>>()
    var isLoading = MutableLiveData<Boolean>().setDefaultValue(false)
    val _list:  LiveData<List<String>> = list

    fun getDataFromFirebase(){
        isLoading.postValue(true)
        repository.getValues(object :Repository.ICallback<List<String>,String>{
            override fun onSuccess(response: List<String>) {
                list.postValue(response)
                isLoading.postValue(false)
            }

            override fun onFailure(failure: String) {
                isLoading.postValue(false)
            }
        })
    }
    fun sendValue(key:String,value:String){
        repository.saveValue(key,value)
    }
}

