package com.pradip.flytebaseasignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.pradip.flytebaseasignment.R
import org.koin.android.ext.android.inject


class SplashFragment : Fragment() {


    val auth: FirebaseAuth by inject()
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val authUser: FirebaseUser? = auth.currentUser
        if (authUser != null) {
            navigateToId(R.id.action_splashFrag_to_mainFrag, null)
        } else {
            navigateToId(R.id.action_splashFrag_to_LoginFrag, null)
        }


    }

    private fun navigateToId(id: Int, bundle: Bundle?) {
        if (navController.currentDestination?.id == R.id.splashFragment)
            if (bundle != null) navController.navigate(id, bundle) else navController.navigate(id)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }

}