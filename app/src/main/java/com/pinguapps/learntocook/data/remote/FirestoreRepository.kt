package com.pinguapps.learntocook.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pinguapps.learntocook.data.Recipe
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
}