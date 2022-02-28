package com.truecaller.data

import android.content.SharedPreferences
import javax.inject.Inject

class LocalStorage
@Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    private companion object {
        const val KEY_USER_TOKEN = "user_token"
        const val KEY_UNIQUE_ID = "unique_id"
    }

    fun setUserToken(token: String) {
        putString(KEY_USER_TOKEN, token)
    }

    fun getUserToken(): String? = getString(KEY_USER_TOKEN)

    fun setUniqueId(uniqueId: String) {
        putString(KEY_UNIQUE_ID, uniqueId)
    }

    private fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit()
            .putBoolean(key, value)
            .apply()
    }

    private fun getBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, false)

    private fun putString(key: String, value: String) {
        sharedPreferences.edit()
            .putString(key, value)
            .apply()
    }

    private fun getString(key: String): String? = sharedPreferences.getString(key, null)

}
