package com.example.myapplication

data class Game(
    val id: String,
    val title: String,
    val imageResId: Int,
    val price: Double,
    var isFavorite: Boolean = false
)
