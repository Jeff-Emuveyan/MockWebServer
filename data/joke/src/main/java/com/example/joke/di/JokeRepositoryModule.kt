package com.example.joke.di

import com.example.joke.JokeRepository
import com.example.joke.JokeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class JokeRepositoryModule {
	
	@Binds
	abstract fun bindJokeRepository(impl: JokeRepositoryImpl): JokeRepository
}