package com.example.mockwebserver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.joke.Screen
import com.example.mockwebserver.ui.theme.MockWebServerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MockWebServerTheme {
				Screen()
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MockWebServerTheme {
		Screen()
	}
}