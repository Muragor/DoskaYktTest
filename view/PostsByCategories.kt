package com.example.doskamura.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doskamura.netWork.DoskaNetWork
import com.example.doskamura.R
import com.example.doskamura.adapter.PostAdapter
import com.example.doskamura.databinding.ActivityMainBinding
import com.example.doskamura.dataclasses.Feed
import com.example.doskamura.dataclasses.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsByCategories : AppCompatActivity() {
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
        binding.root.removeView(binding.setCategory)
        adapter = PostAdapter()
        binding.postsList.layoutManager = LinearLayoutManager(this)
        binding.postsList.adapter = adapter
        adapter.setOnClickListener(object :
              PostAdapter.OnClickListener {
              override fun onClick(position: Int, post1 : Post) {
                  val intent = Intent(this@PostsByCategories, post::class.java)
                  intent.putExtra("post", post1.id)
                  startActivity(intent)
              }
          })
        val cid = intent.getIntExtra("cid", -1)
        val sid = intent.getIntExtra("sid", -1)
        val rid = intent.getIntExtra("rid", -1)
         val feed = DoskaNetWork.instance!!.doskaApi.getPostBySearch(cid, sid, rid).enqueue(object : Callback<Feed> {
             override fun onFailure(call: Call<Feed>, t: Throwable) {
                 Log.v("retrofit", "call failed")
             }
             override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                 adapter.submitList(response.body()?.data?.posts)
             }
         })


    }
}