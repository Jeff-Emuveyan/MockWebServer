package com.example.domain

import com.example.domain.model.Joke
import com.example.joke.JokeRepository
import javax.inject.Inject

class GetJokeUseCase @Inject constructor(private val jokeRepository: JokeRepository) {
	
	suspend operator fun invoke(): Joke? {
		val response = jokeRepository.getJoke() ?: return null
		return Joke("Setup: ${response.setup} \nPunchline: ${response.punchline}")
	}
}