package com.example.joke.remote

import com.example.joke.model.JokeResponse

internal interface RemoteSource {
	
	suspend fun getJoke(): JokeResponse?
}