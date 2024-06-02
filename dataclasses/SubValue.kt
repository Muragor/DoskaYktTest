package com.example.doskamura.dataclasses

import java.io.Serializable

data class SubValue(
    val activeCount: Int,
    val id: Int,
    val isPriority: Boolean,
    val name: String,
    val subOptions: List<Any>
) : Serializable