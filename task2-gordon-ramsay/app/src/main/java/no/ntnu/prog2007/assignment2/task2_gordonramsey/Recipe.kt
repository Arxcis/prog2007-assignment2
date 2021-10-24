package no.ntnu.prog2007.assignment2.task2_gordonramsey

data class Recipe(
    val title: String,
    val description: String,
    val imageURL: String = "<nourl>",
    val tags: ArrayList<String> = arrayListOf())

