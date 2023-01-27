package com.pinguapps.learntocook.data.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.pinguapps.learntocook.data.Ingredient
import com.pinguapps.learntocook.data.Instructions
import com.pinguapps.learntocook.data.Recipe

fun recipeMapToClass(document: DocumentSnapshot): Recipe {
    val name: String = document["name"] as String
    val time: String = document["time"] as String
    val ingredients = arrayListOf<Ingredient>()
    val instructions = Instructions()

    val ingredientList = document["ingredients"] as List<Map<String,String>>

    for (entry: Map<String,String> in ingredientList) {
        val ingredientName: String = entry["name"]!!
        val amount: String = entry["amount"]!!
        val tips: String = entry["tips"] ?: ""
        ingredients.add(Ingredient(ingredientName,amount,tips))
    }


    return Recipe(
        name = name,
        time = time.toInt(),
        ingredients,
        instructions


    )


}