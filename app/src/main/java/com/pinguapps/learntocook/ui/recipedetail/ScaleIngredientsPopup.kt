package com.pinguapps.learntocook.ui.recipedetail

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import com.pinguapps.learntocook.databinding.PopupScaleIngredientsBinding

class ScaleIngredientsPopup(
    private val onConfirm: (Int) -> Unit
) {
    fun showPopup(anchor: View, context: Context) {
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = PopupScaleIngredientsBinding.inflate(inflater)
        val popup = PopupWindow(
            binding.root,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        // Closes the popup window when touch outside.
        popup.isFocusable = true
        // Removes default background.

        popup.showAsDropDown(anchor, 0, 0)

        binding.btnCancelScale.setOnClickListener {
            popup.dismiss()
        }
        binding.btnAcceptScale.setOnClickListener {
            onConfirm(binding.scaleEt.text.toString().toInt())
            popup.dismiss()
        }

    }
}