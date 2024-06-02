package com.example.doskamura.dataclasses

data class Category(
    val code: Int,
    val data: List<CategoryData>,
    val msg: String,
    val result: String
)