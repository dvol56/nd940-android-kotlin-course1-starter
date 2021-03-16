package com.udacity.shoestore.shoe

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.models.Shoe

class ShoeView: LinearLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val binding: ItemShoeBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_shoe, this, false)

    fun loadShoe(shoe: Shoe) {
        binding.apply {
            addView(this.root)
            name.text = shoe.name
            company.text = shoe.company
            shoeSize.text = shoe.size
            description.text = shoe.description
        }
    }
}