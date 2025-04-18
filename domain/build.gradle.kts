plugins {
	kotlin("kapt")
	alias(libs.plugins.serialization)
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.hilt)
}

android {
	namespace = "com.example.domain"
	compileSdk = 35
	
	defaultConfig {
		minSdk = 28
		
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
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
	implementation(project(":data:joke"))
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
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
}