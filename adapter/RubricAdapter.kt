package com.example.doskamura.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.doskamura.databinding.CategoryPatternBinding
import com.example.doskamura.dataclasses.Rubric

class RubricAdapter : ArrayAdapter<Rubric> {
    private var inflater: LayoutInflater? = null
    private var layout = 0
    private var rubrics: List<Rubric>? = null

    constructor(context: Context?, resource: Int, rubrics: List<Rubric>?) : super(context!!, resource, rubrics!!) {
        this.rubrics = rubrics
        this.layout = resource
        this.inflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater!!.inflate(this.layout, parent, false)
        val binding = CategoryPatternBinding.bind(view)
        val rubric: Rubric = rubrics!![position]
        binding.root.text = rubric.name
        return view
    }
}