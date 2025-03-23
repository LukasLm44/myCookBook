package com.example.mycookbook.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycookbook.R



class ListaPrzepisowFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAapter: RecipeAdapter
    private val recipeList = mutableListOf<Recipe>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_przepisow, container, false)
        recyclerView = view.findViewById(R.id.my_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) //układ listy : D
        recipeAapter = RecipeAdapter(recipeList) //  Tworzymy adapter
        recyclerView.adapter = recipeAapter   // Podpinamy adapter do RecyclerView ....
        loadDefaultRecipes() //Dodanie gotowych przepisów do listy



        return view
    }

    class RecipeAdapter(private val recipes: MutableList<Recipe>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

        class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val myRecipeImage: ImageView = itemView.findViewById(R.id.list_Image)
            val myRecipeName: TextView = itemView.findViewById(R.id.list_Name)
            val myRecipeInfo: TextView = itemView.findViewById(R.id.list_info)
            val recipeCategory: TextView = itemView.findViewById(R.id.list_Category)
            val deleteButton: Button = itemView.findViewById(R.id.delete_item_list)
            val saveButton: Button = itemView.findViewById(R.id.saved_item_list)
            val recipeRating: RatingBar = itemView.findViewById(R.id.list_rating)

            //przechowuje dane do widoków pojedynczego elementu
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
            return RecipeViewHolder(view)
            // Tworzy widok dla jednego elementu listy
        }

        override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
            val recipe = recipes[position]
            holder.myRecipeName.text = recipe.name
            holder.myRecipeInfo.text = recipe.info
            holder.recipeCategory.text = recipe.category
            holder.myRecipeImage.setImageResource(recipe.imageRes)
            holder.recipeRating.rating = recipe.rating
            // wypełnia ViewHolder danymi

            holder.deleteButton.setOnClickListener {
                recipes.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, recipes.size)
                //button usuwa elemnty z listy informuje ze adaptera o zmianie


            }

            holder.saveButton.setOnClickListener{

                Toast.makeText(holder.itemView.context, "${recipe.name} ♥ ", Toast.LENGTH_LONG).show()

            }


            holder.recipeRating.setOnRatingBarChangeListener { _, rating, _ ->
                recipes[position].rating = rating
                // Obsługa zmian gwiazdek
            }

        }

        override fun getItemCount() = recipes.size
    }

        fun updateList(newRecipeName: String, newRecipeInfo: String , newRecipeCategory: String , imageRes: Int) {

        recipeList.add(Recipe(newRecipeName, newRecipeInfo,newRecipeCategory, imageRes, 0.0f))
        recipeAapter.notifyDataSetChanged()
    }

    private fun loadDefaultRecipes() {
        val defaultRecipes = listOf(
            Recipe("Drink Mojito", "Orzeźwiający drink z limonką i miętą.", "Napoje", R.drawable.napoje, 0.0f),
            Recipe("Zupa dyniowa", "Kremowa zupa z dyni z nutą imbiru.", "Zupa", R.drawable.zupa, 0.0f),
            Recipe("Ciasto murzynek", "Wilgotne ciasto czekoladowe z polewą.", "Ciasto", R.drawable.ciasto, 0.0f)
        )

        recipeList.addAll(defaultRecipes)
        recipeAapter.notifyDataSetChanged()
    }




}