package com.example.mockwebserver

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class RequestAJokeFlowDispatcher : Dispatcher() {
	
	override fun dispatch(request: RecordedRequest): MockResponse {
		return when (request.path) {
			"/random_joke" ->
				MockResponse().setResponseCode(200)
					.setBody(FileReader.readStringFromFile("joke_response.json"))
			
			else -> MockResponse().setResponseCode(400)
		}
	}
}