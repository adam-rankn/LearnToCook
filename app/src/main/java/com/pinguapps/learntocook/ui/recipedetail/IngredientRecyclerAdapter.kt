package com.pinguapps.learntocook.ui.recipedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pinguapps.learntocook.data.Ingredient
import com.pinguapps.learntocook.data.Recipe
import com.pinguapps.learntocook.databinding.IngredientRowLayoutBinding
import com.pinguapps.learntocook.databinding.RecipeBookRowLayoutBinding

class IngredientRecyclerAdapter(

): RecyclerView.Adapter<IngredientRecyclerAdapter.ViewHolder>(

) {
    var ingredients = listOf<Ingredient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            IngredientRowLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.title.text = ingredient.name
        holder.amount.text = ingredient.amount
        holder.tips.text = ingredient.tips

        val tipsBtn = holder.tipsButton

        if (ingredient.tips == ""){
            tipsBtn.visibility = View.GONE
        }
        else {
            tipsBtn.visibility = View.VISIBLE
        }
        tipsBtn.setOnClickListener {
            holder.tipsView.visibility = View.VISIBLE
        }


    }
    inner class ViewHolder(binding: IngredientRowLayoutBinding): RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private var view: View = binding.root
        val title = binding.name
        val amount = binding.amount
        val tipsView = binding.tipsLayout
        val tips = binding.tips
        val tipsButton = binding.tipsBtn



        init {
            view.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
        }
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

}