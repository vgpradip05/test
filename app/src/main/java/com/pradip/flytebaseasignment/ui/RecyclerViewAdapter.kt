package com.pradip.flytebaseasignment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.pradip.flytebaseasignment.R

class RecyclerViewAdapter(private val items: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    inner class RecyclerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val item = view.findViewById<AppCompatTextView>(R.id.tv_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (items.size < 10) items.size else 10
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.item.text = items[position]
    }
}