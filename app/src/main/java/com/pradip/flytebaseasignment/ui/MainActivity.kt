package com.pradip.flytebaseasignment.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pradip.flytebaseasignment.R
import com.pradip.flytebaseasignment.business.MADSCalculator
import kotlinx.android.synthetic.main.activity_main.*

/*
*
* I have not included data-binding,MVVM Arch, DI and Jetpack libraries in the following project as It is really small and time
* consists of limited time constraint, I can provide access to my personal bitbucket account to notice my work in the
* above tools.
*
* */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}