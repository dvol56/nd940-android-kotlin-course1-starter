package com.udacity.shoestore.shoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class ShoeDetailFragment: Fragment() {

    private val shoesViewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().appBarLayout?.let {
            it.isVisible = true
            it.toolbar.title = getString(R.string.add_shoe)
        }
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.lifecycleOwner = this
        binding.shoeListingsViewModel = shoesViewModel
        binding.shoeNameView.setText("")
        binding.shoeSizeView.setText("")
        binding.companyView.setText("")
        binding.descriptionView.setText("")
        binding.saveButton.setOnClickListener { v: View ->
            if(binding.shoeNameView.text.toString().isNotEmpty() &&  binding.shoeSizeView.text.toString().isNotEmpty() &&  binding.companyView.text.toString().isNotEmpty() && binding.descriptionView.text.toString().isNotEmpty()) {
                shoesViewModel.addShoe(
                    shoe = Shoe(
                        binding.shoeNameView.text.toString(),
                        binding.shoeSizeView.text.toString().toDouble(),
                        binding.companyView.text.toString(),
                        binding.descriptionView.text.toString()
                    )
                )
                Navigation.findNavController(v)
                    .navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
            }else{
                Toast.makeText(context, R.string.fill_in_the_fields, Toast.LENGTH_SHORT).show()
            }
        }
        binding.cancelButton.setOnClickListener { v: View ->
            Navigation.findNavController(v).navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }
        return binding.root
    }
}