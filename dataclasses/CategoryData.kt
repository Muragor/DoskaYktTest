package com.example.doskamura.dataclasses

import java.io.Serializable

data class CategoryData(
    val count: Int,
    val id: Int,
    val isPriority: Boolean,
    val name: String,
    val subcategories: List<Subcategory>
) : Serializable