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

    var arrayList = ArrayList<Pair<String, String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iv_history.setOnClickListener {
            startActivity(Intent(this@MainActivity, ListActivity::class.java).apply {
                putExtra(INTENT_LIST, arrayList)
            })
        }

        bt_calculate.setOnClickListener {
            if (isValidInput(et_input.text.toString())) {
                try {
                    tv_result?.text = MADSCalculator.evaluate(et_input?.text.toString()).toString()
                    arrayList.add(Pair(et_input?.text.toString(), tv_result?.text.toString()))
                } catch (e: Exception) {
                    Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, getString(R.string.valid_ip), Toast.LENGTH_SHORT).show()
            }
        }
        bt_last_ans.setOnClickListener {
            tv_result?.text?.let { last ->
                et_input?.setText(last)
            }
        }

    }

    private fun isValidInput(text: String?): Boolean {
        return if (!text.isNullOrEmpty()) {
            val countOp = text.count { OPERATORS.contains(it) }
            val countSp = text.count { it == ' ' }
            val countDig = text.count { it.isDigit() }
            countOp * 2 == countSp && countDig > 1
        } else
            false

    }

    companion object{
        const val INTENT_LIST = "list"
        const val OPERATORS = "+-/*"
    }

}