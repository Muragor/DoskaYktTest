package com.example.doskamura.categories

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.doskamura.view.PostsByCategories
import com.example.doskamura.R
import com.example.doskamura.categories.Transport.TransportRubrics
import com.example.doskamura.adapter.RubricAdapter
import com.example.doskamura.databinding.ActivityCategoryChoiceBinding
import com.example.doskamura.dataclasses.Rubric

class RubricChoice : AppCompatActivity() {
    lateinit var binding: ActivityCategoryChoiceBinding
    private lateinit var adapter: RubricAdapter
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
        binding.textView.text="Выберите рубрику"
        val cid = intent.getIntExtra("cid", -1)
        val sid = intent.getIntExtra("id_of_subcategory", -1)
        val rubrics = (intent.getSerializableExtra("rubrics") as TransportRubrics).rubrics
        val list_of_rubrics = ArrayList<Rubric>()
        val all_of_rubrics : Rubric = Rubric(0, 0,false,false, "Все рубрики")
        list_of_rubrics.add(all_of_rubrics)
        list_of_rubrics.addAll(rubrics!!)
        adapter = RubricAdapter(this, R.layout.category_pattern, list_of_rubrics)
        binding.categoryList.adapter = adapter
        binding.categoryList.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val rid = adapter.getItem(position)!!.id
                val intentPostByCategories = Intent(this@RubricChoice, PostsByCategories::class.java)
                intentPostByCategories.putExtra("cid", cid)
                intentPostByCategories.putExtra("sid", sid)
                if (rid != 0) {
                intentPostByCategories.putExtra("rid", rid) }
                startActivity(intentPostByCategories)
            }

        })

    }
}