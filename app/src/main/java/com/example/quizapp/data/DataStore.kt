package com.example.quizapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
//import kotlinx.coroutines.flow.internal.NopCollector.emit
import kotlinx.coroutines.flow.map
import java.io.IOException


private const val USER_DATA_STORE = "user_datastore"

private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATA_STORE)

class DataStore(context: Context) {
   companion object{
        private val USER_NAME = stringPreferencesKey("user")
 }

    suspend fun saveUserName(userNameValue: String, context: Context) {
        context.userDataStore.edit { preferences ->
            preferences[USER_NAME] = userNameValue
        }
    }

    suspend fun clearUserName(context: Context) {
        context.userDataStore.edit { preferences ->
            preferences[USER_NAME] = ""
        }
    }

    val userNameFlow: Flow<String> = context.userDataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[USER_NAME] ?: ""

        }
}