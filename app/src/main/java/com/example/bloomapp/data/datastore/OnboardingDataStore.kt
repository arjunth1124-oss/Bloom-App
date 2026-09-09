package com.example.bloomapp.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


private val Context.dataStore by preferencesDataStore(name = "bloom_settings")

@Singleton
class OnboardingDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val ONBOARDING_KEY = booleanPreferencesKey("is_onboarding_completed")

    val isOnboardingCompleted: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[ONBOARDING_KEY] ?: false
        }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_KEY] = completed
        }
    }
}