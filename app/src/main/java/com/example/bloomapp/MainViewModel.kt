
package com.example.bloomapp

import androidx.lifecycle.ViewModel
import com.example.bloomapp.data.datastore.OnboardingDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    onboardingDataStore: OnboardingDataStore
) : ViewModel() {
    val isOnboardingCompleted: Flow<Boolean> = onboardingDataStore.isOnboardingCompleted
}