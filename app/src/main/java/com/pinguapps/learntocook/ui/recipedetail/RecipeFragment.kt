package com.pinguapps.learntocook.ui.recipedetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pinguapps.learntocook.data.local.model.Ingredient
import com.pinguapps.learntocook.databinding.FragmentRecipeBinding
import com.pinguapps.learntocook.ui.RecipeBookViewModel
import com.pinguapps.learntocook.util.parseAmount

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [RecipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!
    private var param1: String? = null
    private val recipeBookViewModel: RecipeBookViewModel by activityViewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val view = binding.root

        val currentRecipe = recipeBookViewModel.currentRecipe

        val recipeImageView = binding.recipeImage
        val recipeDescription = binding.recipeDescription

        Glide.with(requireContext())
            .load(currentRecipe.photo.url)
            .into(recipeImageView)

        recipeDescription.text = currentRecipe.description


        val titleTextView = binding.title
        titleTextView.text = currentRecipe.name

        val ingredientRecyclerView = binding.recyclerIngredients

        val ingredientAdapter = IngredientRecyclerAdapter()

        recipeBookViewModel.currentRecipeScale.observe(viewLifecycleOwner) { scale ->
            val ingredients = currentRecipe.ingredients
            val scaledIngredients = currentRecipe.ingredients.map {
                Ingredient(
                    unit = it.unit,
                    amount= parseAmount(it.unit,it.amount,scale),
                    name = it.name,
                    tips = it.tips
                )
            }
            ingredientAdapter.ingredients = scaledIngredients
            ingredientAdapter.notifyDataSetChanged()

        }
        ingredientAdapter.ingredients = currentRecipe.ingredients

        val scaleIngredientsButton = binding.btnScale

        scaleIngredientsButton.setOnClickListener {
            recipeBookViewModel.currentRecipeScale.postValue(2)

            val popup = ScaleIngredientsPopup { scale ->
                recipeBookViewModel.currentRecipeScale.postValue(scale)
            }
            popup.showPopup(scaleIngredientsButton,requireContext())
        }

        ingredientRecyclerView.adapter = ingredientAdapter
        val ingredientLayoutManager = LinearLayoutManager(requireContext())
        ingredientRecyclerView.layoutManager = ingredientLayoutManager

        val instructionsRecyclerView = binding.recyclerInstructions
        val instructionAdapter = InstructionsRecyclerAdapter()
        instructionAdapter.instructions = currentRecipe.instructions
        instructionsRecyclerView.adapter = instructionAdapter

        val instructionLayoutManager = LinearLayoutManager(requireContext())
        instructionsRecyclerView.layoutManager = instructionLayoutManager

        val ingredientLayout = binding.layoutIngredients
        binding.btnIngredients.setOnClickListener {
            instructionsRecyclerView.visibility = View.GONE
            ingredientLayout.visibility = View.VISIBLE
        }
        binding.btnInstructions.setOnClickListener {
            instructionsRecyclerView.visibility = View.VISIBLE
            ingredientLayout.visibility = View.GONE
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment RecipeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            RecipeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}