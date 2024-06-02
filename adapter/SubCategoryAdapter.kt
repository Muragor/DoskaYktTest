package com.example.doskamura.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.doskamura.databinding.CategoryPatternBinding
import com.example.doskamura.dataclasses.Subcategory


class SubCategoryAdapter : ArrayAdapter<Subcategory> {
    private var inflater: LayoutInflater? = null
    private var layout = 0
    private var subcategories: List<Subcategory>? = null

    constructor(context: Context?, resource: Int, subcategories: List<Subcategory>?) : super(context!!, resource, subcategories!!) {
        this.subcategories = subcategories
        this.layout = resource
        this.inflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater!!.inflate(this.layout, parent, false)
        val binding = CategoryPatternBinding.bind(view)
        val category: Subcategory = subcategories!![position]
        binding.root.text = category.name
        return view
    }
}