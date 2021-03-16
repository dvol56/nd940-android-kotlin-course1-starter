package com.udacity.shoestore.shoe

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class ShoeListFragment: Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().appBarLayout?.let {
            it.isVisible = true
            it.toolbar.title = getString(R.string.list_of_shoes)
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()){
                createShoes(viewModel.shoes.value.orEmpty())
            }
        })

        binding.addShoeButton.setOnClickListener { v: View ->
            Navigation.findNavController(v).navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }
        requireActivity().appBarLayout.toolbar.inflateMenu(R.menu.menu)
        requireActivity().appBarLayout.toolbar.setOnMenuItemClickListener (object : android.widget.Toolbar.OnMenuItemClickListener,
                Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                Navigation.findNavController(view!!).navigate(R.id.action_shoeListFragment_to_loginFragment)
                return true
            }
        })
        return binding.root
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