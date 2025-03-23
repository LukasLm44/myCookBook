package com.example.mycookbook

import android.os.Bundle
import android.widget.ArrayAdapter

import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mycookbook.ui.ListaPrzepisowFragment
import com.example.mycookbook.ui.Recipe


class MainActivity : AppCompatActivity() {


    private lateinit var listaPrzepisowFragment: ListaPrzepisowFragment
    private lateinit var recipeNameEditText: EditText
    private lateinit var recipeInfoEditText: EditText
    private lateinit var addRecipeButton: Button
    private  lateinit var categorySpinner: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        listaPrzepisowFragment = ListaPrzepisowFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentContainer, listaPrzepisowFragment)
            .commit()

        recipeNameEditText = findViewById(R.id.recipe_Name)
        recipeInfoEditText = findViewById(R.id.recipe_Info)
        addRecipeButton = findViewById(R.id.addRecipeButton)
        categorySpinner = findViewById(R.id.categorySpinner)


        val categories = resources.getStringArray(R.array.recipe_categories)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter



            //Obluga buttona Dodaj

            addRecipeButton.setOnClickListener {
            val nameRecipe = recipeNameEditText.text.toString()
            val infoRecpie = recipeInfoEditText.text.toString()
            val selectedCategory = categorySpinner.selectedItem.toString()

            if (selectedCategory == "Wybierz kategorię") {
                Toast.makeText(this, "wybierz kategorię!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            categorySpinner.setSelection(0)
            val imageRes = when (selectedCategory) {
                "Zupa" -> R.drawable.zupa
                "Ciasto" -> R.drawable.ciasto
                "Dania Główne" -> R.drawable.dania_glowne
                "Napoje" -> R.drawable.napoje
                "Inne" -> R.drawable.inne
                else -> R.drawable.def
            }


                if (nameRecipe.isNotEmpty()) {
                listaPrzepisowFragment.updateList(nameRecipe, infoRecpie,selectedCategory, imageRes)
                recipeNameEditText.text.clear()
                recipeInfoEditText.text.clear()
                categorySpinner.setSelection(0)

            } else {
                Toast.makeText(this, "Podaj nazwę przepisu", Toast.LENGTH_SHORT).show()
            }
        }


    }

}





