package com.example.joke.remote

import com.example.joke.model.JokeResponse
import com.example.joke.remote.api.JokesApi
import javax.inject.Inject

internal class RemoteSourceImpl @Inject constructor (private val api: JokesApi): RemoteSource {
	
	override suspend fun getJoke(): JokeResponse? {
		return api.getJoke()
	}
}