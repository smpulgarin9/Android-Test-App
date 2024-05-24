plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.androidtestapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.androidtestapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.retrofit) // Retrofit library for making HTTP requests.
    implementation(libs.converter.gson) // Gson Converter library for Retrofit to convert JSON responses to Kotlin objects.
    implementation(libs.gson) // Gson library for parsing JSON data.
    implementation(libs.androidx.lifecycle.viewmodel.compose) // ViewModel library for Jetpack Compose for managing UI-related data.
    implementation(libs.coil.compose) // Coil library for image loading in Jetpack Compose.
    implementation(libs.androidx.material) // Material Components library for building pull refresh UI with Material Design.
    implementation(libs.androidx.room.runtime) // Room library for local database storage.
    kapt(libs.androidx.room.compiler) // Room compiler for generating Room-related code at compile time.
    implementation(libs.androidx.room.ktx) // Room Kotlin extensions for more concise and idiomatic database access code.

    // Default dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}