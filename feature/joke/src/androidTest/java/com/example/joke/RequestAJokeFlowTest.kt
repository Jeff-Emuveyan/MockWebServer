package com.example.joke

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.core.network.RetrofitClient
import com.example.core.network.di.RetrofitModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit

@UninstallModules(RetrofitModule::class)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class RequestAJokeFlowTest {
	
	@get:Rule
	/***
	 * We have three options to use to launch the composable:
	 * 1) val composeTestRule = createComposeRule()
	 * This won't work because it means we are trying to use
	 * hiltViewModel() (which is inside Screen) outside of an actual Hilt-backed Activity.
	 * In order words, when the Screen() composable is launched and hiltViewModel() is called,
	 * hilt will not be able to create the viewmodel because it needs hiltViewModel() needs an
	 * @AndroidEntryPoint activity to create its dependencies.
	 *
	 * 2) val composeTestRule = createAndroidComposeRule<MainActivity>()
	 * This will work 100% but it means the test can only be run from the module where the MainActivity
	 * is. In this case it will be the app module. We want the freedom to run tests from any module
	 * not just the app module.
	 * ***/
	val composeTestRule = createAndroidComposeRule<TestActivity>()
	/**
	 * This is the best solution because we have created a dedicated TestActivity that is an
	 * @AndroidEntryPoint activity and allows us to write our tests from any module.
	 * ***/
	
	@get:Rule
	var hiltRule = HiltAndroidRule(this)
	private val mockWebServer = MockWebServer()
	
	@Before
	fun setUp() {
		mockWebServer.start(8080)
		composeTestRule.setContent { Screen() }
		composeTestRule.onNodeWithText("Mock Web Server Example").assertIsDisplayed()
	}
	
	@After
	fun tearDown() {
		// It is very important to shutdown the mock server after each test. Else, you will have
		// issues with mockWebServer when there are more than one tests to run.
		mockWebServer.shutdown()
	}
	
	@Test
	fun canRequestAndDisplayAJoke() {
		/***
		 * There are two main ways to set up the response from mock web server when a network request is made:
		 *
		 * 1) mockWebServer.enqueue(MockResponse().setBody(
		 * 			"""
		 * 				{
		 * 				    "setup": "The is a test setup",
		 * 				    "punchline": "This is a test punchline"
		 * 				}
		 * 			""".trimIndent()
		 * 		))
		 * This works but it will pin the mockWebServer to always return the value inside .setBody() anytime a
		 * network call is made. This is good if, during the test, your app will only make a network call to one endpoint.
		 * But if your app will call multiple end points, the method will make mockWebServer return the
		 * exact same json response each time, which is not what we would want.
		 *
		 * 2) mockWebServer.dispatcher = RequestAJokeFlowDispatcher()
		 * The dispatcher allows us to prepare a unique response for each network request.
		 * So if we have multiple endpoints that will be called during our test, the dispatcher makes it
		 * possible for us to prepare a unique json response for each call to those endpoints.
		 * ****/
		mockWebServer.dispatcher = RequestAJokeFlowDispatcher()
		
		composeTestRule.onNodeWithText("Press the button to get a joke").performClick()
		composeTestRule.waitUntil(timeoutMillis = 2_000) {
			composeTestRule
				.onAllNodesWithText(
					"Setup: The is a test setup \nPunchline: This is a test punchline"
				).fetchSemanticsNodes().isNotEmpty()
		}
	}
	
	@Test
	fun displayAnErrorIfNetworkFails() {
		mockWebServer.enqueue(MockResponse().setResponseCode(400))
		
		composeTestRule.onNodeWithText("Press the button to get a joke").performClick()
		composeTestRule.onNodeWithText("Error loading joke").assertIsDisplayed()
	}
	
	@Module
	@InstallIn(SingletonComponent::class)
	class TestModule {
		
		@Provides
		fun provideRetrofit(): Retrofit {
			return RetrofitClient.getRetrofit(baseUrl = "http://127.0.0.1:8080")
		}
	}
}