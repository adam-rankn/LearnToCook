package com.pinguapps.learntocook.data.remote

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pinguapps.learntocook.data.local.model.Recipe
import kotlinx.coroutines.tasks.await

class FirestoreRepository {

    suspend fun getRecipes(): Response<ArrayList<Recipe>> {
        return try {
            val db: FirebaseFirestore = Firebase.firestore
            val recipesRef = db.collection("recipes")

            val recipeList = ArrayList<Recipe>()
            val snapshots = recipesRef.get().await().documents

            for (document in snapshots) {
                val newRecipe = recipeMapToClass(document)
                recipeList.add(newRecipe)
            }

            Response.Success(recipeList)
        } catch (e: Exception) {
            Response.Failure(e)

        }
    }

    suspend fun addRecipe(recipe: Recipe): Response<String> {
        return try {

            val rootRef = FirebaseDatabase.getInstance().reference
            val db: FirebaseFirestore = Firebase.firestore
            val recipesRef = db.collection("recipes")

            val recipesMapList =  recipe.ingredients.map {
                (hashMapOf (
                    "name" to it.name,
                    "amount" to it.amount,
                    "unit" to it.unit,
                    "tips" to it.tips)
                        )
            }

            val instructionsList = recipe.instructions.map {
                it.text
            }

            val hRecipe = hashMapOf(
                "name"  to recipe.name,
                "time" to recipe.time.toString(),
                "ingredients" to recipesMapList,
                "instructions" to instructionsList,
                "photo" to recipe.photo.url,
                "tags" to recipe.tags,
                "diets" to recipe.diets
            )

            val recipeID = recipesRef.add(hRecipe).await().id
            Log.d("info", "Post: $recipeID added")

            Response.Success(recipeID)
        } catch (e: Exception) {
            Log.e("error", "Oh No: $e")
            Response.Failure(e)
        }


    }

}