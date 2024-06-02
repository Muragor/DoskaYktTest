package com.example.doskamura.dataclasses

import java.io.Serializable

data class Subcategory (
    val count: Int,
    val id: Int,
    val isPriority: Boolean,
    val isShowMap: Boolean,
    val name: String,
    val options: List<OptionOfCategory>,
    val rubrics: List<Rubric>
) : Serializable