package com.example.joke

data class UiState(
	val loadingError: Boolean = false,
	val isLoading: Boolean = false,
	val joke: String? = null
)