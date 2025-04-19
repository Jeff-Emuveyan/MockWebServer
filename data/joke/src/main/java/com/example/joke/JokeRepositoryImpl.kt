package com.example.joke

import android.util.Log
import com.example.joke.model.JokeResponse
import com.example.joke.remote.RemoteSource
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

internal class JokeRepositoryImpl @Inject constructor(
	private val remoteSource: RemoteSource,
	private val ioDispatcher: CoroutineDispatcher
): JokeRepository {
	
	override suspend fun getJoke(): JokeResponse? = with(ioDispatcher) {
		try {
			remoteSource.getJoke()
		} catch (e: Exception) {
			Log.e("JEFF", e.message, e)
			null
		}
	}
}