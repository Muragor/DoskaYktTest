package com.example.doskamura.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.doskamura.netWork.DoskaNetWork
import com.example.doskamura.R
import com.example.doskamura.databinding.ActivityPostBinding
import com.example.doskamura.dataclasses.ResPost
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class post : AppCompatActivity() {
    lateinit var binding: ActivityPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_post)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val id = intent.getIntExtra("post", -1)
        val post = DoskaNetWork.instance!!.doskaApi.getPost(id).enqueue(object : Callback<ResPost> {
            override fun onFailure(call: Call<ResPost>, t: Throwable) {
                Log.v("retrofit", "call failed")
            }

            override fun onResponse(call: Call<ResPost>, response: Response<ResPost>) {
                  with(binding) {
                      titleview.text = response.body()!!.data.titleView
                      price.text = response.body()!!.data.price
                      timePost.text = response.body()!!.data.publicDate
                      viewsPost.text = response.body()!!.data.views.toString()
                      izbrPost.text = response.body()!!.data.favorites.toString()
                      mainText.text = response.body()!!.data.text

                      for (image in response.body()!!.data.picsUrl) {
                          val iv = ImageView(this@post)
                          Picasso.get().load(image.url).into(iv)
                          horizontalPics.addView(iv)

                      }
                 }
            }
        })
    }
}