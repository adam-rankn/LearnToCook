package com.pinguapps.learntocook.data.remote

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.pinguapps.learntocook.data.Ingredient
import com.pinguapps.learntocook.data.Instructions
import com.pinguapps.learntocook.data.Recipe

fun recipeMapToClass(document: DocumentSnapshot): Recipe {
    val name: String = document["name"] as String
    val time: String = document["time"] as String
    val ingredients = listOf<Ingredient>(Ingredient())
    val instructions = Instructions()


    return Recipe(
        name = name,
        time = time.toInt(),
        ingredients,
        instructions


    )


}