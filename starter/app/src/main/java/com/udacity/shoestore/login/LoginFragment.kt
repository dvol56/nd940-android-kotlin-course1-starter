package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.activity_main.*


class LoginFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().appBarLayout?.let {
            it.isVisible = false
        }
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.signInButton.setOnClickListener { v: View ->
            if (binding.loginView.text.isNotEmpty() && binding.PasswordView.text.isNotEmpty()) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_welcomeFragment)
            }else{
                Toast.makeText(context, R.string.fill_in_the_fields, Toast.LENGTH_SHORT).show()
            }
        }
        binding.signUpButton.setOnClickListener { v: View ->
            if (binding.loginView.text.isNotEmpty() && binding.PasswordView.text.isNotEmpty()) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_welcomeFragment)
            }else{
                Toast.makeText(context, R.string.fill_in_the_fields, Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}

