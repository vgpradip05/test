package com.pradip.flytebaseasignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pradip.flytebaseasignment.MainViewModel
import com.pradip.flytebaseasignment.R
import com.pradip.flytebaseasignment.business.MADSCalculator
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    var arrayList = ArrayList<Pair<String, String>>()
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

        return inflater.inflate(R.layout.fragment_main, container, false)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        iv_history.setOnClickListener {
            navigateToId(R.id.action_mainFragment_to_ListFragment, null)
        }

        bt_calculate.setOnClickListener {
            if (isValidInput(et_value.text.toString())) {
                try {
                    val result = MADSCalculator.evaluate(et_value?.text.toString()).toString()
                    tv_result?.text = result

                    arrayList.add(Pair(et_value?.text.toString(), tv_result?.text.toString()))
                    viewModel.sendValue(et_value?.text.toString(), result)
                } catch (e: Exception) {
                    Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(context, getString(R.string.valid_ip), Toast.LENGTH_SHORT).show()
            }
        }
        bt_last_ans.setOnClickListener {
            tv_result?.text?.let { last ->
                et_value?.setText(last)
            }
        }
    }

    companion object {
        const val INTENT_LIST = "list"
        const val OPERATORS = "+-/*"
    }

    private fun navigateToId(id: Int, bundle: Bundle?) {
        if (navController.currentDestination?.id == R.id.mainfragment)
            if (bundle != null) navController.navigate(id, bundle) else navController.navigate(id)
    }
}