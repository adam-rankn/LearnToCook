package com.pinguapps.learntocook.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pinguapps.learntocook.R
import com.pinguapps.learntocook.data.Instructions
import com.pinguapps.learntocook.data.Recipe
import com.pinguapps.learntocook.databinding.FragmentRecipeBookBinding

class RecipeBookFragment: Fragment() {

    private var _binding: FragmentRecipeBookBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBookBinding.inflate(inflater, container, false)

        val recipeRecyclerView = binding.recipeBookRecycler
        val recipeLayoutManager = LinearLayoutManager(requireContext())
        recipeRecyclerView.layoutManager = recipeLayoutManager
        val recipeBookAdapter = RecipeBookRecyclerAdapter { recipe ->
            //todo move to recipe page
             }
        recipeRecyclerView.adapter = recipeBookAdapter

        val recipes = mutableListOf<Recipe>()
        for (i in 1..25) {
            val recipe = Recipe("Recipe $i", 30, listOf(), Instructions())
            recipes.add(recipe)
        }
        recipeBookAdapter.recipes = recipes
        recipeBookAdapter.notifyDataSetChanged()
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}