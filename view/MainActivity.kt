package com.example.doskamura.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doskamura.netWork.DoskaNetWork
import com.example.doskamura.R
import com.example.doskamura.adapter.PostAdapter
import com.example.doskamura.categories.CategoryChoice
import com.example.doskamura.databinding.ActivityMainBinding
import com.example.doskamura.dataclasses.Feed
import com.example.doskamura.dataclasses.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: PostAdapter
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapter = PostAdapter()
        binding.postsList.layoutManager = LinearLayoutManager(this)
        binding.postsList.adapter = adapter
        adapter.setOnClickListener(object :
            PostAdapter.OnClickListener {
            override fun onClick(position: Int, post1 : Post) {
                val intent = Intent(this@MainActivity, post::class.java)
                intent.putExtra("post", post1.id)
                startActivity(intent)
            }
        })
        binding.setCategory.setOnClickListener(object : OnClickListener{
            override fun onClick(v: View?) {
             val categoryIntent = Intent(this@MainActivity, CategoryChoice::class.java)
             startActivity(categoryIntent)
            }

        }
        )
        val feed = DoskaNetWork.instance!!.doskaApi.getAllPosts().enqueue(object : Callback<Feed> {
               override fun onFailure(call: Call<Feed>, t: Throwable) {
                   Log.v("retrofit", "call failed")
               }

               override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                   adapter.submitList(response.body()?.data?.posts)
               }
           })

    }
}