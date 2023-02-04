package com.pinguapps.learntocook.data

class Recipe(
    val name: String,
    val time: Int,
    val ingredients: List<Ingredient> = listOf(),
    val instructions: MutableList<Instruction>,
    private val restrictions: List<String> = listOf(),
    val photo: Photo,
    val tags: MutableList<String>
)
{
 fun meetsRestriction(restriction: String): Boolean {
     return restriction in restrictions
 }
}