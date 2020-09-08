package com.pradip.flytebaseasignment.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.pradip.flytebaseasignment.R
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject


class LoginFragment : Fragment() {

    private val auth: FirebaseAuth by inject()
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        bt_login.setOnClickListener {
            login(et_id.text.toString(), et_password.text.toString())
        }
    }

    private fun login(id: String, pwd: String) {

        auth.signInWithEmailAndPassword(id, pwd).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("TAG", "signInWithEmail:success")
                val user = auth.currentUser
                navigateToId(R.id.action_loginFrag_to_MainFrag, null)
                //updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w("TAG", "signInWithEmail:failure", task.exception)
                Toast.makeText(
                    context, "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
                //updateUI(null)
                // ...
            }
        }
    }

    private fun navigateToId(id: Int, bundle: Bundle?) {
        if (navController.currentDestination?.id == R.id.loginFragment)
            if (bundle != null) navController.navigate(id, bundle) else navController.navigate(id)
    }

}