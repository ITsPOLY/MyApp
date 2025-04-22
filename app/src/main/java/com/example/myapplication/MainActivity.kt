package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class MainActivity<Button : View?> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GameRepository.fetchAllGames { success ->
            runOnUiThread {
                if (success) {
                    initializeGameButtons()
                } else {
                    Toast.makeText(this, "Ошибка загрузки игр", Toast.LENGTH_SHORT).show()
                }
            }
        }

        GameRepository.allGames.addAll(
            listOf(
                Game("cs2", "CS2", R.drawable.cs2, 0.0),
                Game("inzoi", "INZOI", R.drawable.inzoi, 39.99),
                Game("mhw", "MHW", R.drawable.mhw, 49.99),
                Game("poe2", "POE2", R.drawable.poe2, 27.75),
                Game("acshadows", "ACSHADOWS", R.drawable.acshadows, 69.99),
                Game("rdr2", "RDR2", R.drawable.rdr2, 59.99),
                Game("haste", "HASTE", R.drawable.haste, 19.50),
                Game("cod", "Call of Duty", R.drawable.cod, 79.99),
                Game("sotf", "SOTF", R.drawable.sotf, 28.99),
                Game("warframe", "WARFRAME", R.drawable.warframe, 0.0),
                Game("enshrouded", "ENSHROUDED", R.drawable.enshrouded, 29.99)
            )
        )

        // Инициализация кнопок и карточек
        initializeGameButtons()
        initializeNavigationIcons()
    }

    private fun initializeGameButtons() {
        // Пример для карточки CS2
        val cardCS2 = findViewById<View>(R.id.cardCS2)
        cardCS2.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "cs2" }
            // Обработчик клика по игре
            Toast.makeText(this, "${game.title} clicked!", Toast.LENGTH_SHORT).show()
        }

        // Обработчик для добавления в избранное (через ImageView)
        val favoriteIconCS2 = findViewById<ImageView>(R.id.favoriteIcon_cs2)
        favoriteIconCS2.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "cs2" }
            GameRepository.addToFavorites(game)
            favoriteIconCS2.setColorFilter(resources.getColor(R.color.green))
            Toast.makeText(this, "${game.title} added to favorites", Toast.LENGTH_SHORT).show()
        }

        favoriteIconCS2.setOnLongClickListener {
            val game = GameRepository.allGames.first { it.id == "cs2" }
            GameRepository.removeFromFavorites(game)
            favoriteIconCS2.clearColorFilter()
            Toast.makeText(this, "${game.title} removed from favorites", Toast.LENGTH_SHORT).show()
            true
        }

        // Обработчик для добавления в корзину (через TextView)
        val gamePriceCS2 = findViewById<TextView>(R.id.gamePrice_cs2)
        gamePriceCS2.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "cs2" }
            GameRepository.addToCart(game)
            Toast.makeText(this, "${game.title} added to cart", Toast.LENGTH_SHORT).show()
        }

        // Пример для карточки INZOI
        val cardINZOI = findViewById<View>(R.id.cardINZOI)
        cardINZOI.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "inzoi" }
            // Обработчик клика по игре
            Toast.makeText(this, "${game.title} clicked!", Toast.LENGTH_SHORT).show()
        }

        // Обработчик для добавления в избранное (через ImageView)
        val favoriteIconINZOI = findViewById<ImageView>(R.id.favoriteIcon_inzoi)
        favoriteIconINZOI.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "inzoi" }
            GameRepository.addToFavorites(game)
            favoriteIconINZOI.setColorFilter(resources.getColor(R.color.green))
            Toast.makeText(this, "${game.title} added to favorites", Toast.LENGTH_SHORT).show()
        }

        favoriteIconINZOI.setOnLongClickListener {
            val game = GameRepository.allGames.first { it.id == "inzoi" }
            GameRepository.removeFromFavorites(game)
            favoriteIconINZOI.clearColorFilter()
            Toast.makeText(this, "${game.title} removed from favorites", Toast.LENGTH_SHORT).show()
            true
        }

        // Обработчик для добавления в корзину (через TextView)
        val gamePriceINZOI = findViewById<TextView>(R.id.gamePrice_inzoi)
        gamePriceINZOI.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "inzoi" }
            GameRepository.addToCart(game)
            Toast.makeText(this, "${game.title} added to cart", Toast.LENGTH_SHORT).show()
        }

        // Пример для карточки MHW
        val cardMHW = findViewById<View>(R.id.cardMHW)
        cardMHW.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "mhw" }
            // Обработчик клика по игре
            Toast.makeText(this, "${game.title} clicked!", Toast.LENGTH_SHORT).show()
        }

        // Обработчик для добавления в избранное (через ImageView)
        val favoriteIconMHW = findViewById<ImageView>(R.id.favoriteIcon_mhw)
        favoriteIconMHW.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "mhw" }
            GameRepository.addToFavorites(game)
            favoriteIconMHW.setColorFilter(resources.getColor(R.color.green))
            Toast.makeText(this, "${game.title} added to favorites", Toast.LENGTH_SHORT).show()
        }

        favoriteIconMHW.setOnLongClickListener {
            val game = GameRepository.allGames.first { it.id == "mhw" }
            GameRepository.removeFromFavorites(game)
            favoriteIconMHW.clearColorFilter()
            Toast.makeText(this, "${game.title} removed from favorites", Toast.LENGTH_SHORT).show()
            true
        }

        // Обработчик для добавления в корзину (через TextView)
        val gamePriceMHW = findViewById<TextView>(R.id.gamePrice_mhw)
        gamePriceMHW.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "mhw" }
            GameRepository.addToCart(game)
            Toast.makeText(this, "${game.title} added to cart", Toast.LENGTH_SHORT).show()
        }

        // Пример для карточки POE2
        val cardPOE2 = findViewById<View>(R.id.cardPOE2)
        cardPOE2.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "poe2" }
            // Обработчик клика по игре
            Toast.makeText(this, "${game.title} clicked!", Toast.LENGTH_SHORT).show()
        }

        // Обработчик для добавления в избранное (через ImageView)
        val favoriteIconPOE2 = findViewById<ImageView>(R.id.favoriteIcon_poe2)
        favoriteIconPOE2.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "poe2" }
            GameRepository.addToFavorites(game)
            favoriteIconPOE2.setColorFilter(resources.getColor(R.color.green))
            Toast.makeText(this, "${game.title} added to favorites", Toast.LENGTH_SHORT).show()
        }

        favoriteIconPOE2.setOnLongClickListener {
            val game = GameRepository.allGames.first { it.id == "poe2" }
            GameRepository.removeFromFavorites(game)
            favoriteIconPOE2.clearColorFilter()
            Toast.makeText(this, "${game.title} removed from favorites", Toast.LENGTH_SHORT).show()
            true
        }

        // Обработчик для добавления в корзину (через TextView)
        val gamePricePOE2 = findViewById<TextView>(R.id.gamePrice_poe2)
        gamePricePOE2.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "poe2" }
            GameRepository.addToCart(game)
            Toast.makeText(this, "${game.title} added to cart", Toast.LENGTH_SHORT).show()
        }

        // Пример для карточки ACSHADOWS
        val cardACSHADOWS = findViewById<View>(R.id.cardACSHADOWS)
        cardACSHADOWS.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "acshadows" }
            // Обработчик клика по игре
            Toast.makeText(this, "${game.title} clicked!", Toast.LENGTH_SHORT).show()
        }

        // Обработчик для добавления в избранное (через ImageView)
        val favoriteIconACSHADOWS = findViewById<ImageView>(R.id.favoriteIcon_acshadows)
        favoriteIconACSHADOWS.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "acshadows" }
            GameRepository.addToFavorites(game)
            favoriteIconACSHADOWS.setColorFilter(resources.getColor(R.color.green))
            Toast.makeText(this, "${game.title} added to favorites", Toast.LENGTH_SHORT).show()
        }

        favoriteIconACSHADOWS.setOnLongClickListener {
            val game = GameRepository.allGames.first { it.id == "acshadows" }
            GameRepository.removeFromFavorites(game)
            favoriteIconACSHADOWS.clearColorFilter()
            Toast.makeText(this, "${game.title} removed from favorites", Toast.LENGTH_SHORT).show()
            true
        }

        // Обработчик для добавления в корзину (через TextView)
        val gamePriceACSHADOWS = findViewById<TextView>(R.id.gamePrice_acshadows)
        gamePriceACSHADOWS.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "acshadows" }
            GameRepository.addToCart(game)
            Toast.makeText(this, "${game.title} added to cart", Toast.LENGTH_SHORT).show()
        }

        // Пример для карточки RDR2
        val cardRDR2 = findViewById<View>(R.id.cardRDR2)
        cardRDR2.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "rdr2" }
            // Обработчик клика по игре
            Toast.makeText(this, "${game.title} clicked!", Toast.LENGTH_SHORT).show()
        }

        // Обработчик для добавления в избранное (через ImageView)
        val favoriteIconRDR2 = findViewById<ImageView>(R.id.favoriteIcon_rdr2)
        favoriteIconRDR2.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "rdr2" }
            GameRepository.addToFavorites(game)
            favoriteIconRDR2.setColorFilter(resources.getColor(R.color.green))
            Toast.makeText(this, "${game.title} added to favorites", Toast.LENGTH_SHORT).show()
        }

        favoriteIconRDR2.setOnLongClickListener {
            val game = GameRepository.allGames.first { it.id == "rdr2" }
            GameRepository.removeFromFavorites(game)
            favoriteIconRDR2.clearColorFilter()
            Toast.makeText(this, "${game.title} removed from favorites", Toast.LENGTH_SHORT).show()
            true
        }

        // Обработчик для добавления в корзину (через TextView)
        val gamePriceRDR2 = findViewById<TextView>(R.id.gamePrice_rdr2)
        gamePriceRDR2.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "rdr2" }
            GameRepository.addToCart(game)
            Toast.makeText(this, "${game.title} added to cart", Toast.LENGTH_SHORT).show()
        }

        // Пример для карточки HASTE
        val cardHASTE = findViewById<View>(R.id.cardHASTE)
        cardHASTE.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "haste" }
            // Обработчик клика по игре
            Toast.makeText(this, "${game.title} clicked!", Toast.LENGTH_SHORT).show()
        }

        // Обработчик для добавления в избранное (через ImageView)
        val favoriteIconHASTE = findViewById<ImageView>(R.id.favoriteIcon_haste)
        favoriteIconHASTE.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "haste" }
            GameRepository.addToFavorites(game)
            favoriteIconHASTE.setColorFilter(resources.getColor(R.color.green))
            Toast.makeText(this, "${game.title} added to favorites", Toast.LENGTH_SHORT).show()
        }

        favoriteIconHASTE.setOnLongClickListener {
            val game = GameRepository.allGames.first { it.id == "haste" }
            GameRepository.removeFromFavorites(game)
            favoriteIconHASTE.clearColorFilter()
            Toast.makeText(this, "${game.title} removed from favorites", Toast.LENGTH_SHORT).show()
            true
        }

        // Обработчик для добавления в корзину (через TextView)
        val gamePriceHASTE = findViewById<TextView>(R.id.gamePrice_haste)
        gamePriceHASTE.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "haste" }
            GameRepository.addToCart(game)
            Toast.makeText(this, "${game.title} added to cart", Toast.LENGTH_SHORT).show()
        }

        // Пример для карточки COD
        val cardCOD = findViewById<View>(R.id.cardCOD)
        cardCOD.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "cod" }
            // Обработчик клика по игре
            Toast.makeText(this, "${game.title} clicked!", Toast.LENGTH_SHORT).show()
        }

        // Обработчик для добавления в избранное (через ImageView)
        val favoriteIconCOD = findViewById<ImageView>(R.id.favoriteIcon_cod)
        favoriteIconCOD.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "cod" }
            GameRepository.addToFavorites(game)
            favoriteIconCOD.setColorFilter(resources.getColor(R.color.green))
            Toast.makeText(this, "${game.title} added to favorites", Toast.LENGTH_SHORT).show()
        }

        favoriteIconCOD.setOnLongClickListener {
            val game = GameRepository.allGames.first { it.id == "cod" }
            GameRepository.removeFromFavorites(game)
            favoriteIconCOD.clearColorFilter()
            Toast.makeText(this, "${game.title} removed from favorites", Toast.LENGTH_SHORT).show()
            true
        }

        // Обработчик для добавления в корзину (через TextView)
        val gamePriceCOD = findViewById<TextView>(R.id.gamePrice_cod)
        gamePriceCOD.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "cod" }
            GameRepository.addToCart(game)
            Toast.makeText(this, "${game.title} added to cart", Toast.LENGTH_SHORT).show()
        }

        // Пример для карточки SOTF
        val cardSOTF = findViewById<View>(R.id.cardSOTF)
        cardSOTF.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "sotf" }
            // Обработчик клика по игре
            Toast.makeText(this, "${game.title} clicked!", Toast.LENGTH_SHORT).show()
        }

        // Обработчик для добавления в избранное (через ImageView)
        val favoriteIconSOTF = findViewById<ImageView>(R.id.favoriteIcon_sotf)
        favoriteIconSOTF.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "sotf" }
            GameRepository.addToFavorites(game)
            favoriteIconSOTF.setColorFilter(resources.getColor(R.color.green))
            Toast.makeText(this, "${game.title} added to favorites", Toast.LENGTH_SHORT).show()
        }

        favoriteIconSOTF.setOnLongClickListener {
            val game = GameRepository.allGames.first { it.id == "sotf" }
            GameRepository.removeFromFavorites(game)
            favoriteIconSOTF.clearColorFilter()
            Toast.makeText(this, "${game.title} removed from favorites", Toast.LENGTH_SHORT).show()
            true
        }

        // Обработчик для добавления в корзину (через TextView)
        val gamePriceSOTF = findViewById<TextView>(R.id.gamePrice_sotf)
        gamePriceSOTF.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "sotf" }
            GameRepository.addToCart(game)
            Toast.makeText(this, "${game.title} added to cart", Toast.LENGTH_SHORT).show()
        }

        // Пример для карточки WARFRAME
        val cardWARFRAME = findViewById<View>(R.id.cardWARFRAME)
        cardWARFRAME.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "warframe" }
            // Обработчик клика по игре
            Toast.makeText(this, "${game.title} clicked!", Toast.LENGTH_SHORT).show()
        }

        // Обработчик для добавления в избранное (через ImageView)
        val favoriteIconWARFRAME = findViewById<ImageView>(R.id.favoriteIcon_warframe)
        favoriteIconWARFRAME.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "warframe" }
            GameRepository.addToFavorites(game)
            favoriteIconWARFRAME.setColorFilter(resources.getColor(R.color.green))
            Toast.makeText(this, "${game.title} added to favorites", Toast.LENGTH_SHORT).show()
        }

        favoriteIconWARFRAME.setOnLongClickListener {
            val game = GameRepository.allGames.first { it.id == "warframe" }
            GameRepository.removeFromFavorites(game)
            favoriteIconWARFRAME.clearColorFilter()
            Toast.makeText(this, "${game.title} removed from favorites", Toast.LENGTH_SHORT).show()
            true
        }

        // Обработчик для добавления в корзину (через TextView)
        val gamePriceWARFRAME = findViewById<TextView>(R.id.gamePrice_warframe)
        gamePriceWARFRAME.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "warframe" }
            GameRepository.addToCart(game)
            Toast.makeText(this, "${game.title} added to cart", Toast.LENGTH_SHORT).show()
        }

        // Пример для карточки ENSHROUDED
        val cardENSHROUDED = findViewById<View>(R.id.cardENSHROUDED)
        cardENSHROUDED.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "enshrouded" }
            // Обработчик клика по игре
            Toast.makeText(this, "${game.title} clicked!", Toast.LENGTH_SHORT).show()
        }

        // Обработчик для добавления в избранное (через ImageView)
        val favoriteIconENSHROUDED = findViewById<ImageView>(R.id.favoriteIcon_enshrouded)
        favoriteIconENSHROUDED.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "enshrouded" }
            GameRepository.addToFavorites(game)
            favoriteIconENSHROUDED.setColorFilter(resources.getColor(R.color.green))
            Toast.makeText(this, "${game.title} added to favorites", Toast.LENGTH_SHORT).show()
        }

        favoriteIconENSHROUDED.setOnLongClickListener {
            val game = GameRepository.allGames.first { it.id == "enshrouded" }
            GameRepository.removeFromFavorites(game)
            favoriteIconENSHROUDED.clearColorFilter()
            Toast.makeText(this, "${game.title} removed from favorites", Toast.LENGTH_SHORT).show()
            true
        }

        // Обработчик для добавления в корзину (через TextView)
        val gamePriceENSHROUDED = findViewById<TextView>(R.id.gamePrice_enshrouded)
        gamePriceENSHROUDED.setOnClickListener {
            val game = GameRepository.allGames.first { it.id == "enshrouded" }
            GameRepository.addToCart(game)
            Toast.makeText(this, "${game.title} added to cart", Toast.LENGTH_SHORT).show()
        }
    }
    fun initializeNavigationIcons() {
        // Иконка корзины
        val cartIcon = findViewById<ImageView>(R.id.cartIcon)
        cartIcon.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        // Иконка избранного
        val favIcon = findViewById<ImageView>(R.id.favIcon)
        favIcon.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }
}

