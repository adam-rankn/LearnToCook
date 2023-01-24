package com.pinguapps.learntocook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pinguapps.learntocook.ui.RecipeBookFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container,RecipeBookFragment()  )
            .commit()
    }
}