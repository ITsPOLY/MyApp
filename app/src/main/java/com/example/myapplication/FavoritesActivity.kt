package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.utils.SharedPrefsHelper
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONArray
import java.io.IOException

class FavoritesActivity : AppCompatActivity() {

    private lateinit var favLayout: LinearLayout
    private val client = OkHttpClient()
    private val baseUrl = "http://10.0.2.2:8081" // для Android-эмулятора

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favs)

        favLayout = findViewById(R.id.cartGameLayout)

        findViewById<TextView>(R.id.backButton).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<ImageView>(R.id.cartIcon).setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        loadFavoritesFromServer()
    }

    private fun loadFavoritesFromServer() {
        val login = SharedPrefsHelper.getLogin(this)
        if (login == null) {
            Toast.makeText(this, "Login not found", Toast.LENGTH_SHORT).show()
            return
        }

        val request = Request.Builder()
            .url("$baseUrl/favorites?login=$login")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@FavoritesActivity, "Failed to load favorites", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string() ?: return
                val jsonArray = JSONArray(body)
                val games = mutableListOf<Game>()

                for (i in 0 until jsonArray.length()) {
                    val gameId = jsonArray.getJSONObject(i).getString("gameId")
                    GameRepository.allGames.find { it.id == gameId }?.let { games.add(it) }
                }

                runOnUiThread {
                    favLayout.removeAllViews()
                    games.forEach { game ->
                        GameRepository.addToFavorites(game)
                        favLayout.addView(createFavoriteView(game))
                    }
                }
            }
        })
    }

    // Создаем вид для игры в избранном
    private fun createFavoriteView(game: Game): View {
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

        return view
    }

    private fun removeFavoriteFromServer(game: Game) {
        val request = Request.Builder()
            .url("$baseUrl/favorites/remove")
            .delete(RequestBody.create(
                "application/json".toMediaTypeOrNull(), """
                {
                    "login": "user123",
                    "gameId": "${game.id}"
                }
            """.trimIndent()))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@FavoritesActivity, "Failed to remove", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    runOnUiThread {
                        GameRepository.removeFromFavorites(game)
                        Toast.makeText(this@FavoritesActivity, "${game.title} removed", Toast.LENGTH_SHORT).show()
                        recreate() // перезагрузим экран
                    }
                }
            }
        })
    }
}
