package com.pradip.flytebaseasignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pradip.flytebaseasignment.MainViewModel
import com.pradip.flytebaseasignment.R
import com.pradip.flytebaseasignment.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    val viewModel: MainViewModel by sharedViewModel()
    private lateinit var binding: FragmentListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list: ArrayList<String> = ArrayList()
        viewModel.getDataFromFirebase()

        viewModel._list.observe(this, Observer {
            list.addAll(it)
            rv_list.adapter?.notifyDataSetChanged()
        })

        rv_list.layoutManager = LinearLayoutManager(context)
        rv_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        list.reverse()
        rv_list.adapter = RecyclerViewAdapter(list)
    }
}