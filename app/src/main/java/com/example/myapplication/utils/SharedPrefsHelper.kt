package com.example.myapplication.utils

import android.content.Context

object SharedPrefsHelper {
    private const val PREFS_NAME = "steam_app_prefs"
    private const val KEY_LOGIN = "user_login"

    fun saveLogin(context: Context, login: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LOGIN, login).apply()
    }

    fun getLogin(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_LOGIN, null)
    }
}