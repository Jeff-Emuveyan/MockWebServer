package com.example.core.network.di

import com.example.core.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
	
	@Provides
	fun provideRetrofit(): Retrofit {
		return RetrofitClient.getRetrofit(baseUrl = "https://official-joke-api.appspot.com")
	}
}