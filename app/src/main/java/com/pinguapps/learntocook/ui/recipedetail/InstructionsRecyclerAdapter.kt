package com.pinguapps.learntocook.ui.recipedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pinguapps.learntocook.data.local.model.Instruction
import com.pinguapps.learntocook.databinding.InstructionRowLayoutBinding


class InstructionsRecyclerAdapter(

): RecyclerView.Adapter<InstructionsRecyclerAdapter.ViewHolder>(

) {
    var instructions = listOf<Instruction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            InstructionRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val instruction = instructions[position]

        holder.instructionStep.text = instruction.text


    }
    inner class ViewHolder(binding: InstructionRowLayoutBinding): RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private val view: View = binding.root

        val instructionStep = binding.text


        init {
            view.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
        }
    }

    override fun getItemCount(): Int {
        return instructions.size
    }

}