package com.e.e_commerce.prefernce

import android.content.Context
import android.content.SharedPreferences


object AppPreferences {
    private const val NAME = "E-commerce"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences
    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)

    }
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }
    var api_token: String?
        get() = preferences.getString(ConstantPreference.TOKEN, "")
        set(value) = preferences.edit {
            it.putString(ConstantPreference.TOKEN, value)
        }



}