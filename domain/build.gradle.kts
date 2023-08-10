plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    namespace = "com.example.domain"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion
        testInstrumentationRunner = AppConfig.androidJUnitRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        apiVersion = AppConfig.apiVersion
        languageVersion = AppConfig.languageVersion
        jvmTarget = AppConfig.jvmTarget
    }
    compileOptions {
        sourceCompatibility = AppConfig.sourceCompatibility
        targetCompatibility = AppConfig.targetCompatibility
    }
}

dependencies {
    implementation(project(":data"))
    koin()
    kotlin()
    coroutines()
    retrofit2()
    timber()
    testLibraries()
}