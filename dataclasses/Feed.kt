package com.example.doskamura.dataclasses

data class Feed(
    val code: Int,
    val data: FeedData,
    val msg: String,
    val result: String
)