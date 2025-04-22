package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import okhttp3.Request
import kotlinx.serialization.json.put
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val totalTextView = findViewById<TextView>(R.id.totalTextView)
        val purchaseButton = findViewById<Button>(R.id.purchaseButton)

        // Обработчик кнопки "Назад"
        val backButton = findViewById<TextView>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val price = GameRepository.getTotalPrice()
        val formattedPrice = String.format("%.2f", price)

        // Обновляем общую цену
        totalTextView.text = "Total: ${formattedPrice}€"

        // Обработчик кнопки покупки
        val purchaseId = java.util.UUID.randomUUID().toString()
        val gameIds = GameRepository.cart.map { it.id }
        val userLogin = "demo_user" // Здесь будет логин пользователя (можно хранить в SharedPreferences)
        val totalAmount = GameRepository.getTotalPrice()

        val jsonObject = kotlinx.serialization.json.buildJsonObject {
            put("id", purchaseId)
            put("userLogin", userLogin)
            put("gameIds", kotlinx.serialization.json.JsonArray(gameIds.map { kotlinx.serialization.json.JsonPrimitive(it) }))
            put("totalAmount", totalAmount)
        }

        val jsonBody = jsonObject.toString()
        val requestBody = jsonBody.toRequestBody("application/json".toMediaType())

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://10.0.2.2:8081/purchase") // ← для эмулятора Android
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@CartActivity, "Failed to purchase", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    runOnUiThread {
                        GameRepository.purchaseGames()
                        GameRepository.clearCart()
                        Toast.makeText(this@CartActivity, "Purchase completed!", Toast.LENGTH_SHORT).show()
                        finish()
                        startActivity(Intent(this@CartActivity, MainActivity::class.java))
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@CartActivity, "Server error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        // Заполняем корзину списком игр
        val cartGameLayout = findViewById<LinearLayout>(R.id.cartGameLayout)
        for (game in GameRepository.cart) {
            val gameView = createGameView(game) // Создаем карточку игры
            cartGameLayout.addView(gameView)  // Добавляем карточку игры в контейнер
        }

        val favIcon = findViewById<ImageView>(R.id.favIcon)
        favIcon.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }

    // Создаем вид для игры в корзине
    private fun createGameView(game: Game): View {
        // Загружаем разметку для карточки игры в зависимости от ID игры
        val view = when (game.id) {
            "cs2" -> LayoutInflater.from(this).inflate(R.layout.game_card_cs2, null)
            "inzoi" -> LayoutInflater.from(this).inflate(R.layout.game_card_inzoi, null)
            "cod" -> LayoutInflater.from(this).inflate(R.layout.game_card_cod, null)
            "acshadows" -> LayoutInflater.from(this).inflate(R.layout.game_card_acshadows, null)
            "enshrouded" -> LayoutInflater.from(this).inflate(R.layout.game_card_enshrouded, null)
            "haste" -> LayoutInflater.from(this).inflate(R.layout.game_card_haste, null)
            "mhw" -> LayoutInflater.from(this).inflate(R.layout.game_card_mhw, null)
            "poe2" -> LayoutInflater.from(this).inflate(R.layout.game_card_poe2, null)
            "rdr2" -> LayoutInflater.from(this).inflate(R.layout.game_card_rdr2, null)
            "sotf" -> LayoutInflater.from(this).inflate(R.layout.game_card_sotf, null)
            else -> LayoutInflater.from(this).inflate(R.layout.game_card_warframe, null)
        }

        // Привязываем элементы в карточке через их уникальные ID
        val gameTitle = when (game.id) {
            "cs2" -> view.findViewById<TextView>(R.id.gameTitle_cs2)
            "inzoi" -> view.findViewById<TextView>(R.id.gameTitle_inzoi)
            "cod" -> view.findViewById<TextView>(R.id.gameTitle_cod)
            "acshadows" -> view.findViewById<TextView>(R.id.gameTitle_acshadows)
            "enshrouded" -> view.findViewById<TextView>(R.id.gameTitle_enshrouded)
            "haste" -> view.findViewById<TextView>(R.id.gameTitle_haste)
            "mhw" -> view.findViewById<TextView>(R.id.gameTitle_mhw)
            "poe2" -> view.findViewById<TextView>(R.id.gameTitle_poe2)
            "rdr2" -> view.findViewById<TextView>(R.id.gameTitle_rdr2)
            "sotf" -> view.findViewById<TextView>(R.id.gameTitle_sotf)
            else -> view.findViewById<TextView>(R.id.gameTitle_warframe)
        }

        val gamePrice = when (game.id) {
            "cs2" -> view.findViewById<TextView>(R.id.gamePrice_cs2)
            "inzoi" -> view.findViewById<TextView>(R.id.gamePrice_inzoi)
            "cod" -> view.findViewById<TextView>(R.id.gamePrice_cod)
            "acshadows" -> view.findViewById<TextView>(R.id.gamePrice_acshadows)
            "enshrouded" -> view.findViewById<TextView>(R.id.gamePrice_enshrouded)
            "haste" -> view.findViewById<TextView>(R.id.gamePrice_haste)
            "mhw" -> view.findViewById<TextView>(R.id.gamePrice_mhw)
            "poe2" -> view.findViewById<TextView>(R.id.gamePrice_poe2)
            "rdr2" -> view.findViewById<TextView>(R.id.gamePrice_rdr2)
            "sotf" -> view.findViewById<TextView>(R.id.gamePrice_sotf)
            else -> view.findViewById<TextView>(R.id.gamePrice_warframe)
        }

        val favoriteIcon = when (game.id) {
            "cs2" -> view.findViewById<ImageView>(R.id.favoriteIcon_cs2)
            "inzoi" -> view.findViewById<ImageView>(R.id.favoriteIcon_inzoi)
            "cod" -> view.findViewById<ImageView>(R.id.favoriteIcon_cod)
            "acshadows" -> view.findViewById<ImageView>(R.id.favoriteIcon_acshadows)
            "enshrouded" -> view.findViewById<ImageView>(R.id.favoriteIcon_enshrouded)
            "haste" -> view.findViewById<ImageView>(R.id.favoriteIcon_haste)
            "mhw" -> view.findViewById<ImageView>(R.id.favoriteIcon_mhw)
            "poe2" -> view.findViewById<ImageView>(R.id.favoriteIcon_poe2)
            "rdr2" -> view.findViewById<ImageView>(R.id.favoriteIcon_rdr2)
            "sotf" -> view.findViewById<ImageView>(R.id.favoriteIcon_sotf)
            else -> view.findViewById<ImageView>(R.id.favoriteIcon_warframe)
        }

        val removeButton = when (game.id) {
            "cs2" -> view.findViewById<ImageView>(R.id.delIcon_cs2)
            "inzoi" -> view.findViewById<ImageView>(R.id.delIcon_inzoi)
            "cod" -> view.findViewById<ImageView>(R.id.delIcon_cod)
            "acshadows" -> view.findViewById<ImageView>(R.id.delIcon_acshadows)
            "enshrouded" -> view.findViewById<ImageView>(R.id.delIcon_enshrouded)
            "haste" -> view.findViewById<ImageView>(R.id.delIcon_haste)
            "mhw" -> view.findViewById<ImageView>(R.id.delIcon_mhw)
            "poe2" -> view.findViewById<ImageView>(R.id.delIcon_poe2)
            "rdr2" -> view.findViewById<ImageView>(R.id.delIcon_rdr2)
            "sotf" -> view.findViewById<ImageView>(R.id.delIcon_sotf)
            else -> view.findViewById<ImageView>(R.id.delIcon_warframe)
        }

        // Устанавливаем данные для карточки
        gameTitle.text = game.title
        gamePrice.text = "${game.price}€"

        // Показываем кнопку удаления только для игр в корзине
        removeButton.visibility = View.VISIBLE
        removeButton.setOnClickListener {
            GameRepository.removeFromCart(game)
            Toast.makeText(this, "${game.title} removed from cart", Toast.LENGTH_SHORT).show()
            // Обновляем корзину после удаления
            refreshCartView()
        }

        favoriteIcon.setOnClickListener {
            GameRepository.addToFavorites(game)
            favoriteIcon.setColorFilter(resources.getColor(R.color.green))
            Toast.makeText(this, "${game.title} added to favorites", Toast.LENGTH_SHORT).show()
        }

        favoriteIcon.setOnLongClickListener {
            GameRepository.removeFromFavorites(game)
            favoriteIcon.clearColorFilter()
            Toast.makeText(this, "${game.title} removed from favorites", Toast.LENGTH_SHORT).show()
            true
        }

        return view
    }

    // Перезагружаем активность после удаления игры
    fun refreshCartView() {
        val cartGameLayout = findViewById<LinearLayout>(R.id.cartGameLayout)
        cartGameLayout.removeAllViews()  // Очищаем текущие карточки
        for (game in GameRepository.cart) {
            val gameView = createGameView(game)  // Перезаполняем корзину с кнопкой удаления
            cartGameLayout.addView(gameView)
        }
    }
}