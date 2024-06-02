package com.example.doskamura.dataclasses

data class FeedData(
    val categories: List<PostsCategory>,
    val count: Int,
    val organizations: List<Any>,
    val posts: List<Post>,
    val token: String
)