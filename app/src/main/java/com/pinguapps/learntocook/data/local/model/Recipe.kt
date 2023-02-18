package com.pinguapps.learntocook.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
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

