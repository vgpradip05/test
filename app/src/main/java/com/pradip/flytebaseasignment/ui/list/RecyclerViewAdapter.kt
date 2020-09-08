package com.pradip.flytebaseasignment.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.pradip.flytebaseasignment.BR
import androidx.recyclerview.widget.RecyclerView
import com.pradip.flytebaseasignment.R

class RecyclerViewAdapter(private val items: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    inner class RecyclerViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(value: String) {
            binding.setVariable(BR.value, value)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view: ViewDataBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_layout,
            parent,
            false
        )

        return RecyclerViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return if (items.size < 10) items.size else 10
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.onBind(items[position])
    }
}