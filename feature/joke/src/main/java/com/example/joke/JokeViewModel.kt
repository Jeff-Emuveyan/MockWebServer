package com.example.joke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.GetJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(private val getJokeUseCase: GetJokeUseCase) : ViewModel() {
	
	private val _uiState = MutableStateFlow(UiState())
	val uiState: StateFlow<UiState> = _uiState.asStateFlow()
	
	fun getJoke() = viewModelScope.launch {
		_uiState.update { it.copy(isLoading = true) }
		val joke = getJokeUseCase()
		_uiState.update {
			it.copy(isLoading = false, joke = joke?.value, loadingError = joke == null)
		}
	}
}