package com.udacity.shoestore.shoe

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment: Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()){
                createShoes(viewModel.shoes.value.orEmpty())
            }
        })

        binding.addShoeButton.setOnClickListener { v: View ->
            Navigation.findNavController(v).navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, view?.findNavController()!!) || super.onOptionsItemSelected(item)
    }

    private fun createShoes(shoes: List<Shoe>){
        val shoeContainer = binding.shoesLayout
        shoes.forEach { shoe ->
            val shoeLayout = context?.let { ShoeView(it) }
            shoeLayout?.loadShoe(shoe)
            shoeContainer.addView(shoeLayout)
        }
    }
}