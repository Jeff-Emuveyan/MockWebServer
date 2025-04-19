plugins {
	kotlin("kapt")
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
	alias(libs.plugins.hilt)
}

android {
	namespace = "com.example.mockwebserver"
	compileSdk = 35
	
	defaultConfig {
		applicationId = "com.example.mockwebserver"
		minSdk = 28
		targetSdk = 35
		versionCode = 1
		versionName = "1.0"
		
		testInstrumentationRunner = "com.example.mockwebserver.CustomTestRunner"
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
	buildFeatures {
		compose = true
	}
}

dependencies {
	implementation(project(":core"))
	implementation(project(":feature:joke"))
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	implementation(libs.androidx.runner)
	implementation(libs.hilt.android.testing)
	implementation(libs.hilt.android.testing)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
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
	// mockwebserver
	androidTestImplementation(libs.mockwebserver)
	// retrofit
	implementation(libs.retrofit)
	implementation(libs.retrofit.converter.gson)
	implementation(libs.okhttp)
	implementation(libs.okhttp.logging.interceptor)
	implementation(libs.okhttp.converter.scalars)
}