package com.pinguapps.learntocook.data.remote

import com.google.firebase.firestore.DocumentSnapshot
import com.pinguapps.learntocook.data.Ingredient
import com.pinguapps.learntocook.data.Instruction
import com.pinguapps.learntocook.data.Photo
import com.pinguapps.learntocook.data.Recipe

fun recipeMapToClass(document: DocumentSnapshot): Recipe {
    val name: String = document["name"] as String
    val time: String = document["time"] as String
    val ingredients = arrayListOf<Ingredient>()
    val url = document["photo"] ?: ""
    val photo  = Photo(url as String)

    val ingredientList = document["ingredients"] as List<Map<String,String>>

    for (entry: Map<String,String> in ingredientList) {
        val ingredientName: String = entry["name"]!!
        val amount: String = entry["amount"]!!
        val tips: String = entry["tips"] ?: ""
        ingredients.add(Ingredient(ingredientName,amount,tips))
    }

    val instructions = mutableListOf<Instruction>()
    val instructionsList = document["instructions"] as List<String>

    for (entry in instructionsList) {
        val instruction = Instruction(entry)
        instructions.add(instruction)
    }


    return Recipe(
        name = name,
        time = time.toInt(),
        ingredients = ingredients,
        instructions = instructions,
        photo = photo,
        tags = mutableListOf()
    )


}