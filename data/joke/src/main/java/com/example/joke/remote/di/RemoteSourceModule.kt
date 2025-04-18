package com.example.joke.remote.di

import com.example.joke.remote.RemoteSource
import com.example.joke.remote.RemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteSourceModule {
	
	@Binds
	abstract fun bindRemoteSource(impl: RemoteSourceImpl): RemoteSource
}