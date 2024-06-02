package com.example.doskamura.categories.Transport

import com.example.doskamura.dataclasses.Rubric
import java.io.Serializable

class TransportRubrics(rubrics: List<Rubric>) : Serializable {
    var rubrics : List<Rubric>?= rubrics
}