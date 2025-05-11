package org.socoding.astroarchive.feature.mediasearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.socoding.astroarchive.core.domain.onError
import org.socoding.astroarchive.core.domain.onSuccess
import org.socoding.astroarchive.feature.mediasearch.domain.repository.MediaSearchRepository

class MediaSearchViewModel(
    private val mediaSearchRepository: MediaSearchRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MediaSearchState())
    val state = _state
        .onStart {
            observeSearchQuery()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _state.value
        )

    private var searchJob: Job? = null

    fun onAction(action: MediaSearchAction) {
        when (action) {
            is MediaSearchAction.OnQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
            is MediaSearchAction.OnMediaItemClick -> {
                _state.update {
                    it.copy(
                        selectedMediaItem = action.mediaItem
                    )
                }
                observeIsFavourite()
            }
            is MediaSearchAction.OnSaveClick -> {
                changeSelectedItemSavedState()
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                items = emptyList(),
                                errorMessage = null
                            )
                        }
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchMedia(query)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun searchMedia(text: String) = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true,
                searchQuery = text
            )
        }
        mediaSearchRepository
            .searchMedia(text)
            .onSuccess { result ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        items = result.items ?: emptyList()
                    )
                }
            }
            .onError {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = it.errorMessage
                    )
                }
            }
    }

    fun changeSelectedItemSavedState() {
        viewModelScope.launch {
            if (state.value.selectedMediaItem?.isSaved == true) {
                mediaSearchRepository.removeMediaItem(state.value.selectedMediaItem?.id ?: "")
            } else {
                state.value.selectedMediaItem?.let {
                    mediaSearchRepository.saveMediaItem(it)
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

    private fun observeIsFavourite() {
        state.value.selectedMediaItem?.id?.let {
            mediaSearchRepository
                .isMediaItemSaved(it)
                .onEach { isSaved ->
                    _state.update { it.copy(
                        selectedMediaItem = it.selectedMediaItem?.copy(isSaved = isSaved)
                    ) }
                }
                .launchIn(viewModelScope)
        }
    }
}