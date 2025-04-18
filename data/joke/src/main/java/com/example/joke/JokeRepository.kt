package com.example.joke

import com.example.joke.model.JokeResponse

interface JokeRepository {
	
	suspend fun getJoke(): JokeResponse?
}