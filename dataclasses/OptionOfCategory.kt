package com.example.doskamura.dataclasses

import java.io.Serializable

data class OptionOfCategory(
    val id: Int,
    val multiselect: Boolean,
    val name: String,
    val required: Boolean,
    val type: String,
    val unit: String,
    val values: List<Value>
) : Serializable