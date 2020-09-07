package com.pradip.flytebaseasignment.data

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.pradip.flytebaseasignment.business.MADSCalculator

class Repository(private val ref: DatabaseReference) {

    fun getValues(callback: ICallback<List<String>, String>) {

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value as (HashMap<String, String>)
                Log.d("TAG", "Value is: $value")
                val array = ArrayList<String>()
                value.entries.forEach {
                    array.add("${MADSCalculator.decryptFromFirebase(it.key)} = ${it.value}")
                }
                callback.onSuccess(array)

            }
        })
    }

    fun saveValue(key: String, value: String) {
        ref.child(MADSCalculator.encryptForFirebase(key)).setValue(value)
    }

    companion object {
        const val KEY_NAME = "calculations"
        @Volatile
        private var instance: Repository? = null

        fun getInstance(ref: DatabaseReference) =
            instance ?: synchronized(this) {
                instance
                    ?: Repository(ref).also { instance = it }
            }
    }

    interface ICallback<T, R> {
        abstract fun onSuccess(response: T)
        abstract fun onFailure(failure: R)
    }
}