package com.pradip.flytebaseasignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pradip.flytebaseasignment.R
import com.pradip.flytebaseasignment.ui.MainActivity.Companion.INTENT_LIST
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val list = intent?.extras?.get(INTENT_LIST) as ArrayList<Pair<String, String>>
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        list.reverse()
        rv_list.adapter = RecyclerViewAdapter(list)
    }
}