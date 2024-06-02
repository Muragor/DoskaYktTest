package com.example.doskamura.categories.Transport

import com.example.doskamura.dataclasses.Subcategory
import java.io.Serializable

class TransportSubCategories(subcategories: List<Subcategory>) : Serializable {
    var subcategories : List<Subcategory>?= subcategories
}