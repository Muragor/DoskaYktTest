package com.example.doskamura.dataclasses

import java.io.Serializable

data class Value(
    val activeCount: Int,
    val id: Int,
    val isPriority: Boolean,
    val name: String,
    val subOptions: List<SubOption>
) : Serializable