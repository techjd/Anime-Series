import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "2.3.0"
}

android {
    namespace = "com.techjd.animeseries"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.techjd.animeseries"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // retrofit & okhttp - for making network requests and logging
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    // dagger hilt - for dependency injection
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    // navigation extension for hilt - to inject viewmodels in compose navigation
    implementation(libs.androidx.hilt.navigation.compose)

    // kotlinx serialization - for parsing json
    implementation(libs.kotlinx.serialization.json)
    // kotlinx - serialization converter for retrofit
    implementation(libs.converter.kotlinx.serialization)

    // coil - for loading images
    implementation(libs.coil.compose)
    // coil okhttp - for using coil with okhttp client
    implementation(libs.coil.network.okhttp)

    // roomdb - for local data storage
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    // For using Room with Paging 3
    implementation(libs.androidx.room.paging)

    // paging 3 - for pagination
    implementation(libs.androidx.paging.runtime)
    // compose support for paging 3
    implementation(libs.androidx.paging.compose)

    // YouTube Player
    implementation(libs.core)

    // navigation 3
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.material3.adaptive.navigation3)
    implementation(libs.kotlinx.serialization.core)
}