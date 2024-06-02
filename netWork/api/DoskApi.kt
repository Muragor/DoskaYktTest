package com.example.doskamura.netWork.api
import com.example.doskamura.dataclasses.Category
import com.example.doskamura.dataclasses.Feed
import com.example.doskamura.dataclasses.ResPost
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DoskApi {
 @GET("/v4/feed")
fun getAllPosts() : Call<Feed>
@GET("/v4/feed")
fun getPostBySearch(@Query("cid") cid : Int = 0, @Query("sid") sid : Int = 0, @Query("rid") rid : Int = 0) : Call<Feed>
@GET("/v4/post")
fun getPost(@Query("id") id : Int) : Call<ResPost>
@GET("/v4/categories?scope=simple")
fun getCategories() : Call<Category>
} 