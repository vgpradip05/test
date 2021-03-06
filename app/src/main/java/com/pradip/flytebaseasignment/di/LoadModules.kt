package com.pradip.flytebaseasignment.di


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.pradip.flytebaseasignment.data.Repository
import com.pradip.flytebaseasignment.viewmodel.ListViewModel
import com.pradip.flytebaseasignment.data.Repository.Companion.KEY_NAME
import com.pradip.flytebaseasignment.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
val appModule = module {
    single{
        Firebase.database.getReference(KEY_NAME)
    }
    single {
        Repository.getInstance(get())
    }
    single {FirebaseAuth.getInstance() }
    viewModel { ListViewModel(get()) }
    viewModel { MainViewModel(get()) }
}
