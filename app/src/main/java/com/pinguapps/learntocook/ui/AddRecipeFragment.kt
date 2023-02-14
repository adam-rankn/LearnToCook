package com.pinguapps.learntocook.ui

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.pinguapps.learntocook.R
import com.pinguapps.learntocook.data.Ingredient
import com.pinguapps.learntocook.data.Instruction
import com.pinguapps.learntocook.data.Recipe
import com.pinguapps.learntocook.data.getDiets
import com.pinguapps.learntocook.data.remote.FirestoreRepository
import com.pinguapps.learntocook.databinding.FragmentAddRecipeBinding
import com.pinguapps.learntocook.databinding.FragmentRecipeBookBinding
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [AddRecipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRecipeFragment : Fragment() {

    private var _binding: FragmentAddRecipeBinding? = null
    private val binding get() = _binding!!
    val recipe = Recipe()

    val diets = getDiets()
    val ingredients = mutableListOf<Ingredient>()
    val instructions = mutableListOf<Instruction>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddRecipeBinding.inflate(inflater, container, false)

        val dietTxt = binding.edtDiet
        val dietBtn = binding.addDiet

        val strucEdit = binding.edtInstruction
        val instructionsBtn = binding.addInstruction

        val ingredientBtn = binding.addIngredient
        val tipEdt = binding.edtTip
        val amtEdt = binding.edtAmount
        val grdEdt = binding.edtIngredient
        val unitEdt = binding.edtUnit

        val timeEdt = binding.recipeTime
        val nameEdt = binding.recipeName

        val addBtn = binding.addRecipeBtn


        ingredientBtn.setOnClickListener {
            val name = grdEdt.text.toString()
            val amount = amtEdt.text.toString()
            val tip = tipEdt.text.toString()
            val unit = unitEdt.text.toString()
            ingredients.add(Ingredient(
                name = name,
                amount = amount,
                tips = tip,
                unit = unit
                ))

            grdEdt.text = null
            amtEdt.text = null
            tipEdt.text = null
            unitEdt.text = null

        }

        instructionsBtn.setOnClickListener {
            val step = strucEdit.text.toString()
            val struc = Instruction(step)
            instructions.add(struc)
            strucEdit.text = null

        }

        dietBtn.setOnClickListener {
            diets[dietTxt.text.toString()] = true
            dietTxt.text = null
        }

        addBtn.setOnClickListener {

            val addRecipe = Recipe(name= nameEdt.text.toString(),
            time = timeEdt.text.toString().toInt(),
            ingredients = ingredients,
            instructions = instructions,
            diets = diets as HashMap<String, Boolean>,)

            val fb = FirestoreRepository()
            lifecycleScope.launch {
                fb.addRecipe(addRecipe)
            }

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}