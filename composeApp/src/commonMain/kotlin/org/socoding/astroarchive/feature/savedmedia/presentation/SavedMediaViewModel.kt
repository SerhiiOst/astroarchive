package org.socoding.astroarchive.feature.savedmedia.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.socoding.astroarchive.core.domain.onError
import org.socoding.astroarchive.core.domain.onSuccess
import org.socoding.astroarchive.feature.savedmedia.domain.repository.SavedMediaRepository

class SavedMediaViewModel(
    private val savedMediaRepository: SavedMediaRepository
) : ViewModel() {

    private var observeSavedMediaJob: Job? = null

    private val _state = MutableStateFlow(SavedMediaState())
    val state = _state
        .onStart {
            observeSavedMedia()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _state.value
        )

    fun onAction(action: SavedMediaAction) {
        when (action) {
            is SavedMediaAction.UpdateMediaSavedStatus -> {
                changeSelectedItemSavedState()
            }
            is SavedMediaAction.OnMediaItemClick -> {
                _state.update {
                    it.copy(
                        selectedMediaItem = action.mediaItem
                    )
                }
            }
        }
    }

    private fun observeSavedMedia() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        observeSavedMediaJob?.cancel()
        observeSavedMediaJob = savedMediaRepository
            .getSavedMedia()
            .onEach { list ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        items = list
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun changeSelectedItemSavedState() {
        viewModelScope.launch {
            if (state.value.selectedMediaItem?.isSaved == true) {
                savedMediaRepository.removeMediaItem(state.value.selectedMediaItem?.id ?: "")
                _state.update { it.copy(selectedMediaItem = it.selectedMediaItem?.copy(isSaved = false)) }
            } else {
                state.value.selectedMediaItem?.let {
                    savedMediaRepository.saveMediaItem(it)
                        .onSuccess {
                            _state.update { it.copy(selectedMediaItem = it.selectedMediaItem?.copy(isSaved = true)) }
                        }
                        .onError {
                            _state.update {
                                it.copy(
                                    errorMessage = it.errorMessage
                                )
                            }
                        }
                }
            }
        }
    }
}