package com.pinguapps.learntocook.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pinguapps.learntocook.data.Recipe
import com.pinguapps.learntocook.databinding.RecipeBookRowLayoutBinding

class RecipeBookRecyclerAdapter(
    private val onItemClicked: (Recipe) -> Unit,
): RecyclerView.Adapter<RecipeBookRecyclerAdapter.ViewHolder>(

) {
    var recipes = listOf<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecipeBookRowLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]

        holder.recipeName.text = recipe.name
        holder.recipeTime.text = recipe.time.toString()

        holder.recipeName.setOnClickListener {
            onItemClicked(recipe)
        }

        holder.recipeImage.setOnClickListener {
            onItemClicked(recipe)
        }
    }
    inner class ViewHolder(binding: RecipeBookRowLayoutBinding): RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private var view: View = binding.root
        val recipeName = binding.recipeRowName
        val recipeTime = binding.recipeRowTime
        val recipeImage = binding.recipeRowImage

        init {
            view.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

}