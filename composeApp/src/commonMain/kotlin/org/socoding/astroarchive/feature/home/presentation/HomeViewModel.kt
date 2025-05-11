package org.socoding.astroarchive.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.socoding.astroarchive.core.domain.onError
import org.socoding.astroarchive.core.domain.onSuccess
import org.socoding.astroarchive.feature.home.domain.repository.HomeRepository

class HomeViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state
        .onStart {
            getApod()
        }
        .stateIn(
            viewModelScope,
            kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            _state.value
        )

    private fun getApod() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            homeRepository
                .getApod()
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            apod = result
                        )
                    }
                }
                .onError {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = it.errorMessage,
                            apod = null
                        )
                    }
                }
        }
    }
}