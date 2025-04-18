package com.example.mockwebserver

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
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
	
	@Test
	fun canRequestAndDisplayAJoke() {
		composeTestRule.setContent { Screen() }
		composeTestRule.onNodeWithText("Mock Web Server Example").assertIsDisplayed()
	}
	
	@Module
	@InstallIn(SingletonComponent::class)
	class TestModule {
		
		@Provides
		fun provideRetrofit(): Retrofit {
			return RetrofitClient.getRetrofit(baseUrl = "http://127.0.0.1:1234")
		}
	}
}