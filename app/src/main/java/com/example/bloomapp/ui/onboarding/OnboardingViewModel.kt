package com.example.bloomapp.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bloomapp.data.ads.AdManager
import com.example.bloomapp.data.datastore.OnboardingDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingDataStore: OnboardingDataStore,
    private val adManager: AdManager
) : ViewModel() {

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

    fun onNextPage(totalPages: Int, onComplete: () -> Unit) {
        if (_currentPage.value < totalPages - 1) {
            _currentPage.value += 1
        } else {
            completeOnboarding(onComplete)
        }
    }

    fun completeOnboarding(onComplete: () -> Unit) {
        viewModelScope.launch {
            onboardingDataStore.setOnboardingCompleted(true)
            adManager.isOnboardingCompleted = true
            adManager.fetchAppOpenAd() // Preload App Open Ad for future launches
            onComplete()
        }
    }
}