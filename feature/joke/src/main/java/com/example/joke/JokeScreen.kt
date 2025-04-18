package com.example.joke

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Screen() {
	val viewModel: JokeViewModel = hiltViewModel()
	val uiState = viewModel.uiState.collectAsState()
	Screen(uiState.value) {
		viewModel.getJoke()
	}
}

@Composable
internal fun Screen(uiState: UiState, getJoke: () -> Unit) {
	Column(
		modifier = Modifier.fillMaxSize().padding(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Text("Mock Web Server Example", color = Color.Magenta)
		Spacer(Modifier.height(30.dp))
		if (uiState.joke != null) Text(uiState.joke, textAlign = TextAlign.Center)
		if (uiState.loadingError) Text("Error loading joke", color = Color.Red)
		Spacer(Modifier.height(20.dp))
		if (uiState.isLoading) {
			CircularProgressIndicator()
		} else {
			Button(onClick = getJoke) {
				Text("Press the button to get a joke")
			}
		}
	}
}