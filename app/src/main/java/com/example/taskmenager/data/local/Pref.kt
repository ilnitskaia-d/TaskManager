package com.example.taskmenager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.net.Uri

class Pref(context : Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    fun isShowed() : Boolean {
        return pref.getBoolean(SHOWED_KEY, false)
    }

    fun onBoardingShow() {
        pref.edit().putBoolean(SHOWED_KEY, true).apply()
    }

    fun safeImg(img: Uri?) {
        pref.edit().putString(IMG_KEY, img.toString()).apply()
    }

    fun getImg(): String?{
        return pref.getString(IMG_KEY, "")
    }

    fun saveName(name : String) {
        pref.edit().putString(NAME_KEY, name).apply()
    }

    fun getName(): String? {
        return pref.getString(NAME_KEY, "")
    }
    companion object {
        const val PREF_NAME = "task.manager.pref"
        const val SHOWED_KEY = "showed.key"
        const val NAME_KEY = "name.key"
        const val IMG_KEY = "img.key"
    }
}