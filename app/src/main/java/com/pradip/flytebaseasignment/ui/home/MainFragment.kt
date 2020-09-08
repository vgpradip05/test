package com.pradip.flytebaseasignment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pradip.flytebaseasignment.viewmodel.ListViewModel
import com.pradip.flytebaseasignment.R
import com.pradip.flytebaseasignment.business.MADSCalculator
import com.pradip.flytebaseasignment.databinding.FragmentMainBinding
import com.pradip.flytebaseasignment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by sharedViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        iv_history.setOnClickListener {
            navigateToId(R.id.action_mainFragment_to_ListFragment, null)
        }
        viewModel.error.observe(this, Observer {
            if(!it.isNullOrBlank()){
                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
            }
        })

    }
    companion object {
        const val OPERATORS = "+-/*"
        const val VALID_IP = "Enter valid input !"
        const val ERROR = "Error while calculation"
    }

    private fun navigateToId(id: Int, bundle: Bundle?) {
        if (navController.currentDestination?.id == R.id.mainfragment)
            if (bundle != null) navController.navigate(id, bundle) else navController.navigate(id)
    }

    override fun onPause() {
        super.onPause()
    }
}