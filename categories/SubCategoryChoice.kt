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
import com.example.doskamura.categories.Transport.TransportSubCategories
import com.example.doskamura.adapter.SubCategoryAdapter
import com.example.doskamura.databinding.ActivityCategoryChoiceBinding
import com.example.doskamura.dataclasses.OptionOfCategory
import com.example.doskamura.dataclasses.Rubric
import com.example.doskamura.dataclasses.Subcategory

class SubCategoryChoice : AppCompatActivity() {
    lateinit var binding: ActivityCategoryChoiceBinding
    private lateinit var adapter: SubCategoryAdapter
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
        binding.textView.text="Выберите подкатегорию"
        val cid = intent.getIntExtra("id_of_category", -1)
        val subCategories = (intent.getSerializableExtra("subcategories") as TransportSubCategories).subcategories
        val list_of_subcategories = ArrayList<Subcategory>()
        val not_rubrics = ArrayList<Rubric>()
        val not_options = ArrayList<OptionOfCategory>()
        val all_of_subcategories : Subcategory = Subcategory(0, 0, false,
            false, "Все подкатегории", not_options, not_rubrics)
        list_of_subcategories.add(all_of_subcategories)
        adapter = SubCategoryAdapter(this, R.layout.category_pattern, list_of_subcategories)
        list_of_subcategories.addAll(subCategories!!)
        binding.categoryList.adapter = adapter
        binding.categoryList.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val id = adapter.getItem(position)!!.id
                if (id==0) {
                    val intentPostByCategories = Intent(this@SubCategoryChoice, PostsByCategories::class.java)
                    intentPostByCategories.putExtra("cid", cid)
                    startActivity(intentPostByCategories)
                }
                else {
                val rubrics = adapter.getItem(position)!!.rubrics
                val intentRubric = Intent(this@SubCategoryChoice, RubricChoice::class.java)
                intentRubric.putExtra("cid", cid)
                intentRubric.putExtra("id_of_subcategory", id)
                intentRubric.putExtra("rubrics", TransportRubrics(rubrics))
                startActivity(intentRubric) }
            }

        })
    }
}