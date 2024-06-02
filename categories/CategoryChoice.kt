package com.example.doskamura.categories

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.doskamura.netWork.DoskaNetWork
import com.example.doskamura.view.PostsByCategories
import com.example.doskamura.R
import com.example.doskamura.categories.Transport.TransportSubCategories
import com.example.doskamura.adapter.CategoryAdapter
import com.example.doskamura.databinding.ActivityCategoryChoiceBinding
import com.example.doskamura.dataclasses.Category
import com.example.doskamura.dataclasses.CategoryData
import com.example.doskamura.dataclasses.Subcategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryChoice : AppCompatActivity() {
    lateinit var binding: ActivityCategoryChoiceBinding
    private lateinit var adapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCategoryChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val list_of_categories = ArrayList<CategoryData>()
        val not_subcategories = ArrayList<Subcategory>()
        val all_of_categories : CategoryData = CategoryData(0, 0, false, "Все категории", not_subcategories)
        list_of_categories.add(all_of_categories)
        adapter = CategoryAdapter(this, R.layout.category_pattern, list_of_categories)
        val categoryChoice = DoskaNetWork.instance!!.doskaApi.getCategories().enqueue(object : Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                list_of_categories.addAll(response.body()!!.data)
                binding.categoryList.adapter = adapter
                binding.categoryList.setOnItemClickListener(object : OnItemClickListener {
                    override fun onItemClick(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val id = adapter.getItem(position)!!.id
                        if (id==0) {
                            val intentPostByCategories = Intent(this@CategoryChoice, PostsByCategories::class.java)
                            startActivity(intentPostByCategories)
                        }
                        else {
                        val subcategory = adapter.getItem(position)!!.subcategories
                        val intentSubCategory = Intent(this@CategoryChoice, SubCategoryChoice::class.java)
                        intentSubCategory.putExtra("id_of_category", id)
                        intentSubCategory.putExtra("subcategories", TransportSubCategories(subcategory))
                        startActivity(intentSubCategory) }
                    }

                })
            }
            override fun onFailure(call: Call<Category>, t: Throwable) {
                Log.v("retrofit", "call failed")
            }
        })

    }
}


