package no.ntnu.prog2007.assignment2.task2_gordonramsey

import android.graphics.Bitmap
import java.io.Serializable

data class Recipe(
    val title: String,
    val description: String,
    val imageURL: String,
    val categories: ArrayList<String>) : Serializable

