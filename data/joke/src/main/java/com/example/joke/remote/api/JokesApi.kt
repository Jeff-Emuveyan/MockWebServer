package com.example.joke.remote.api

import com.example.joke.model.JokeResponse
import retrofit2.http.GET

interface JokesApi {
	
	@GET("random_joke")
	suspend fun getJoke(): JokeResponse?
}