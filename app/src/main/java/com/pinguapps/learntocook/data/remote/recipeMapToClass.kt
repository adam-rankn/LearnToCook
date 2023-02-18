package com.pinguapps.learntocook.data.remote

import com.google.firebase.firestore.DocumentSnapshot
import com.pinguapps.learntocook.data.local.model.Ingredient
import com.pinguapps.learntocook.data.local.model.Instruction
import com.pinguapps.learntocook.data.local.model.Photo
import com.pinguapps.learntocook.data.local.model.Recipe

fun recipeMapToClass(document: DocumentSnapshot): Recipe {
    val name: String = document["name"] as String
    val time: String = document["time"] as String
    val ingredients = arrayListOf<Ingredient>()
    val url = document["photo"] ?: ""
    val photo  = Photo(url as String)
    val description: String = (document["description"] ?: "") as String

    val ingredientList = document["ingredients"] as List<Map<String,String>>

    for (entry: Map<String,String> in ingredientList) {
        val ingredientName: String = entry["name"]!!
        val amount: String = entry["amount"]!!
        val unit: String = entry["unit"] ?: ""
        val tips: String = entry["tips"] ?: ""
        ingredients.add(Ingredient(ingredientName,amount,unit,tips))
    }

    val instructions = mutableListOf<Instruction>()
    val instructionsList = document["instructions"] as List<String>

    for (entry in instructionsList) {
        val instruction = Instruction(entry)
        instructions.add(instruction)
    }

    val dietsMap = document["diets"] as HashMap<String,Boolean> ?: hashMapOf()


    return Recipe(
        name = name,
        time = time.toInt(),
        ingredients = ingredients,
        instructions = instructions,
        photo = photo,
        tags = mutableListOf(),
        diets = dietsMap,
        description = description

    )


}