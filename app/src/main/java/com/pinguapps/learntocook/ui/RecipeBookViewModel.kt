package com.pinguapps.learntocook.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinguapps.learntocook.data.Instruction
import com.pinguapps.learntocook.data.Photo
import com.pinguapps.learntocook.data.Recipe
import com.pinguapps.learntocook.data.remote.FirestoreRepository
import com.pinguapps.learntocook.data.remote.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeBookViewModel: ViewModel() {

    private val repository = FirestoreRepository()

    val searchText = ""

    var currentRecipe = Recipe(name = "", instructions = mutableListOf(), time = 30, photo = Photo(""), tags = mutableListOf())

    val allRecipes = MutableLiveData(mutableListOf<Recipe>())
    val recipes = MutableLiveData(mutableListOf<Recipe>())

    init {
        //get all recipes
        viewModelScope.launch {
            when (val response = repository.getRecipes()) {
                is Response.Loading -> {
                    //loading
                }
                is Response.Success -> {
                    recipes.postValue(response.data)
                    allRecipes.postValue(response.data)
                }
                is Response.Failure -> {
                    Log.e("PROFILE", "failed to load recipes: ${response.e}")
                }
            }
        }
    }

    fun filterBySearch(text: String) {
        val filteredList = arrayListOf<Recipe>()
        if (text == ""){
            recipes.postValue(allRecipes.value)
        }
        else {
            for (recipe in allRecipes.value!!) {
                if (recipe.name.lowercase().contains(text)) {
                    filteredList.add(recipe)
                }
                //TODO check ingredients etc
            }
            recipes.postValue(filteredList)
        }

    }
}