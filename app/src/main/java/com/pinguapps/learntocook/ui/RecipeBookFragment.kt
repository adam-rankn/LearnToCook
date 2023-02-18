package com.pinguapps.learntocook.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.pinguapps.learntocook.R
import com.pinguapps.learntocook.data.local.model.Recipe
import com.pinguapps.learntocook.databinding.FragmentRecipeBookBinding
import com.pinguapps.learntocook.ui.recipedetail.RecipeFragment
import com.pinguapps.learntocook.util.isTablet

class RecipeBookFragment: Fragment() {

    private var _binding: FragmentRecipeBookBinding? = null
    private val binding get() = _binding!!
    private val recipeBookViewModel: RecipeBookViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBookBinding.inflate(inflater, container, false)

        val recipeRecyclerView = binding.recipeBookRecycler
        val recipeLayoutManager = LinearLayoutManager(requireContext())
        recipeRecyclerView.layoutManager = recipeLayoutManager
        val recipeBookAdapter = RecipeBookRecyclerAdapter { recipe ->
            recipeBookViewModel.currentRecipe = recipe
            navigateToFragment(RecipeFragment())
             }
        recipeRecyclerView.adapter = recipeBookAdapter


        recipeBookViewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            recipeBookAdapter.recipes = recipes as ArrayList<Recipe>
            recipeBookAdapter.notifyDataSetChanged()

        }

        val search = binding.recipeSearch

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                recipeBookViewModel.updateSearchText(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                recipeBookViewModel.updateSearchText(query)
                return true
            }
        })


        val showFiltersButton = binding.btnDiet
        val dietLayout = binding.dietFilterLay

        showFiltersButton.setOnClickListener {
            if (dietLayout.visibility == GONE) {
                dietLayout.visibility = VISIBLE
            }
            else if (dietLayout.visibility == VISIBLE) {
                dietLayout.visibility = GONE
            }
        }

        val dietChipGroup = binding.dietChipGroup

        for (chip in dietChipGroup){
            if (chip is Chip){

                chip.setOnCheckedChangeListener { _, isChecked ->
                    recipeBookViewModel.updateDietFilter(chip.tag.toString(), isChecked)
                }
            }

        }

        val addbtn = binding.btnAdd

        addbtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, AddRecipeFragment()).addToBackStack("home")
                .commit()
        }

        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navigateToFragment(fragment: Fragment){
        if (isTablet(requireContext())){
            //TODO for tablet go to recipe preview in right frame
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, fragment).addToBackStack("home")
                .commit()
        }
        else {
            parentFragmentManager.beginTransaction()
                .add(R.id.container, fragment).addToBackStack("home")
                .hide(this).commit()
        }
    }
}