package com.example.doskamura.dataclasses

import java.io.Serializable

data class Rubric(
    val count: Int,
    val id: Int,
    val isPriority: Boolean,
    val isShowMap: Boolean,
    val name: String
) : Serializable