package com.pinguapps.learntocook.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinguapps.learntocook.data.Recipe
import com.pinguapps.learntocook.data.remote.FirestoreRepository
import com.pinguapps.learntocook.data.remote.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeBookViewModel: ViewModel() {

    private val repository = FirestoreRepository()

    private val _allRecipes = MutableStateFlow(listOf<Recipe>())
    val allRecipes: StateFlow<List<Recipe>> = _allRecipes

    val recipes = MutableLiveData(listOf<Recipe>())

    init {
        //get all recipes
        viewModelScope.launch {
            when (val response = repository.getRecipes()) {
                is Response.Loading -> {
                    //loading
                }
                is Response.Success -> {
                    recipes.postValue(response.data)
                }
                is Response.Failure -> {
                    Log.e("PROFILE", "failed to load recipes: ${response.e}")
                }
            }
        }
    }
}