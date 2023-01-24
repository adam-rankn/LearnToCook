package com.pinguapps.learntocook.data

class Recipe (
    val name: String,
    val time: Int,
    val ingredients: List<Ingredient> = listOf(),
    val instructions: Instructions,
    private val restrictions: List<Restriction> = listOf(),
    val photo: Photo? = null
)
{
 fun meetsRestriction(restriction: Restriction): Boolean {
     return restriction in restrictions
 }
}