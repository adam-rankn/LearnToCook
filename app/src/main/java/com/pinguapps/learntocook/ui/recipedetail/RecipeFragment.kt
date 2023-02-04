package com.pinguapps.learntocook.ui.recipedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pinguapps.learntocook.data.Recipe
import com.pinguapps.learntocook.databinding.FragmentRecipeBinding
import com.pinguapps.learntocook.ui.RecipeBookViewModel

// TODO: Rename parameter arguments, choose names that match
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val view = binding.root
        val currentRecipe = recipeBookViewModel.currentRecipe
        val titleTextView = binding.title
        titleTextView.text = currentRecipe.name

        val ingredientRecyclerView = binding.recyclerIngredients

        val ingredientAdapter = IngredientRecyclerAdapter()
        ingredientAdapter.ingredients = currentRecipe.ingredients
        ingredientRecyclerView.adapter = ingredientAdapter

        val ingredientLayoutManager = LinearLayoutManager(requireContext())
        ingredientRecyclerView.layoutManager = ingredientLayoutManager

        val instructionsRecyclerView = binding.recyclerInstructions
        val instructionAdapter = InstructionsRecyclerAdapter()
        instructionAdapter.instructions = currentRecipe.instructions
        instructionsRecyclerView.adapter = instructionAdapter

        val instructionLayoutManager = LinearLayoutManager(requireContext())
        instructionsRecyclerView.layoutManager = instructionLayoutManager

        binding.btnIngredients.setOnClickListener {
            instructionsRecyclerView.visibility = View.GONE
            ingredientRecyclerView.visibility = View.VISIBLE
        }
        binding.btnInstructions.setOnClickListener {
            instructionsRecyclerView.visibility = View.VISIBLE
            ingredientRecyclerView.visibility = View.GONE
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