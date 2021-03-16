package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class InstructionsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentInstructionsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)
        binding.okButton.setOnClickListener { v: View ->
            Navigation.findNavController(v).navigate(R.id.action_instructionsFragment_to_shoeListFragment)
        }
        return binding.root
    }
}