package com.pinguapps.learntocook.data

class Recipe(
    val name: String = "",
    val description: String = "",
    val time: Int = 30,
    val ingredients: MutableList<Ingredient> = mutableListOf(),
    val instructions: MutableList<Instruction> = mutableListOf(),
    private val restrictions: List<String> = listOf(),
    val photo: Photo = Photo("url"),
    val tags: MutableList<String> = mutableListOf(),
    val diets: HashMap<String, Boolean> = hashMapOf()
)

