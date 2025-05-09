plugins {
	kotlin("kapt")
	alias(libs.plugins.serialization)
	alias(libs.plugins.hilt)
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.compose.compiler)
}

android {
	namespace = "com.example.joke"
	compileSdk = 35
	
	defaultConfig {
		minSdk = 28
		
		testInstrumentationRunner = "com.example.joke.CustomTestRunner"
		consumerProguardFiles("consumer-rules.pro")
	}
	
	buildFeatures {
		viewBinding = true
		compose = true
	}
	
	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = "11"
	}
}

dependencies {
	
	implementation(project(":core"))
	implementation(project(":domain"))
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	implementation(libs.androidx.ui.viewbinding)
	implementation(libs.hilt.android.testing)
	implementation(libs.androidx.runner)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
	// navigation graph
	implementation(libs.navigation.graph)
	// hilt
	implementation(libs.hilt)
	kapt(libs.hilt.compiler)
	kapt(libs.androidx.hilt.compiler)
	// hilt test
	kaptTest(libs.hilt.compiler)
	androidTestImplementation(libs.hilt.android.testing)
	kaptAndroidTest(libs.hilt.compiler)
	// hilt navigation compose
	implementation(libs.hilt.navigation.compose)
	// serialization
	implementation(libs.kotlinx.serialization.json)
	implementation("javax.inject:javax.inject:1")
	
	// viewmodel compose
	implementation(libs.viewmodel.compose)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.permission)
	implementation(libs.gson)
	
	// mockwebserver
	androidTestImplementation(libs.mockwebserver)
	// retrofit
	implementation(libs.retrofit)
	implementation(libs.retrofit.converter.gson)
	implementation(libs.okhttp)
	implementation(libs.okhttp.logging.interceptor)
	implementation(libs.okhttp.converter.scalars)
}