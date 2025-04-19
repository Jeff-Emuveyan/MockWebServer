package com.example.mockwebserver

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.core.network.RetrofitClient
import com.example.core.network.di.RetrofitModule
import com.example.joke.Screen
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
	//val composeTestRule = createComposeRule() // won't work because it means we are trying to use
	// hiltViewModel() (which is inside Screen) outside of an actual Hilt-backed Activity.
	val composeTestRule = createAndroidComposeRule<TestActivity>()
	//val composeTestRule = createAndroidComposeRule<MainActivity>()
	
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
		mockWebServer.shutdown()
	}
	
	@Test
	fun canRequestAndDisplayAJoke() {
		/*mockWebServer.enqueue(MockResponse().setBody(
			"""
				{
				    "setup": "The is a test setup",
				    "punchline": "This is a test punchline"
				}
			""".trimIndent()
		))*/
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