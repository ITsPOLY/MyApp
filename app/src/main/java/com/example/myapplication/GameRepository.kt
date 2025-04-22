package com.example.myapplication

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

object GameRepository {
    val allGames = mutableListOf<Game>()
    val favorites = mutableListOf<Game>()
    val cart = mutableListOf<Game>()
    val purchasedGames = mutableListOf<Game>()

    private val client = OkHttpClient()
    private const val BASE_URL = "http://10.0.2.2:8081" // для Android-эмулятора

    fun fetchAllGames(onResult: (Boolean) -> Unit) {
        val request = Request.Builder()
            .url("$BASE_URL/games")
            .get()
            .build()

        Thread {
            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val json = response.body?.string()
                    val gameListType = object : TypeToken<List<Game>>() {}.type
                    val games: List<Game> = Gson().fromJson(json, gameListType)

                    allGames.clear()
                    allGames.addAll(games)

                    onResult(true)
                } else {
                    onResult(false)
                }
            } catch (e: IOException) {
                onResult(false)
            }
        }.start()
    }

    fun addToFavorites(game: Game) {
        if (!favorites.contains(game)) favorites.add(game)
    }

    fun isInFavorites(game: Game): Boolean = favorites.contains(game)

    fun removeFromFavorites(game: Game) {
        favorites.remove(game)
    }

    fun addToCart(game: Game) {
        if (!cart.contains(game)) cart.add(game)
    }

    fun removeFromCart(game: Game) {
        cart.remove(game)
    }

    fun clearCart() {
        cart.clear()
    }

    fun purchaseGames() {
        purchasedGames.addAll(cart)
        clearCart()
    }

    fun getTotalPrice(): Double = cart.sumOf { it.price }
}

