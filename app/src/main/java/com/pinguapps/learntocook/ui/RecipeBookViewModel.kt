package com.pinguapps.learntocook.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinguapps.learntocook.data.Instruction
import com.pinguapps.learntocook.data.Photo
import com.pinguapps.learntocook.data.Recipe
import com.pinguapps.learntocook.data.getDiets
import com.pinguapps.learntocook.data.remote.FirestoreRepository
import com.pinguapps.learntocook.data.remote.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeBookViewModel: ViewModel() {

    private val repository = FirestoreRepository()

    var searchText = ""

    var currentRecipe = Recipe()
    val currentRecipeScale = MutableLiveData(1)


    val allRecipes = MutableLiveData(mutableListOf<Recipe>())
    val recipes = MutableLiveData(mutableListOf<Recipe>())

    val currentFilters = getDiets()

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
                    Log.e("FIRESTORE", "failed to load recipes: ${response.e}")
                }
            }
        }
    }

    private fun updateDisplayList() {
        val filteredList = arrayListOf<Recipe>()
        if (searchText == "" && !currentFilters.containsValue(true)){
            recipes.postValue(allRecipes.value)
        }
        else {
            for (recipe in allRecipes.value!!) {
                if (recipe.name.lowercase().contains(searchText)
                    || recipe.tags.contains(searchText.lowercase())  ) {

                    //check diet restrictions
                    var matchesFilters = true
                    for (diet in currentFilters){
                        if (diet.value == true){
                            if (recipe.diets[diet.key] == false){
                                matchesFilters = false
                            }
                        }
                    }
                    if (matchesFilters){
                        filteredList.add(recipe)
                    }
                }
                //TODO check ingredients etc
            }
            recipes.postValue(filteredList)
        }

    }

    fun updateDietFilter(diet: String, isActive: Boolean){
        currentFilters[diet] = isActive
        updateDisplayList()
    }

    fun updateSearchText(query: String){
        searchText = query
        updateDisplayList()
    }
}