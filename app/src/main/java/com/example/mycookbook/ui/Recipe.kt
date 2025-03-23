package com.example.mycookbook.ui

data class Recipe(

    val name: String,
    val info: String,
    val category: String,
    val imageRes: Int,
    var rating: Float = 1.0f



)