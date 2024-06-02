package com.example.doskamura.dataclasses

import java.io.Serializable

data class SubOption(
    val id: Int,
    val multiselect: Boolean,
    val name: String,
    val required: Boolean,
    val type: String,
    val values: List<SubValue>
) : Serializable