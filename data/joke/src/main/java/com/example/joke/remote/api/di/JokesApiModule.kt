package com.example.joke.remote.api.di

import com.example.joke.model.JokeResponse
import com.example.joke.remote.api.JokesApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

internal class JokesApiImpl @Inject constructor (private val retrofit: Retrofit): JokesApi {
	
	override suspend fun getJoke(): JokeResponse? {
		return retrofit.create<JokesApi>().getJoke()
	}
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CreatePostApiModule {
	
	@Binds
	abstract fun bindApi(impl: JokesApiImpl): JokesApi
}