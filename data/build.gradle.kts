plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    namespace = "com.example.data"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion
        testInstrumentationRunner = AppConfig.androidJUnitRunner
    }
    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://gateway.marvel.com/v1/public/\"")
            buildConfigField("String", "API_KEY", "\"b7e14bab409c70a5c4e7c2b319c09d7b\"")
            buildConfigField("String", "TS", "\"1682982412\"")
            buildConfigField("String", "HASH", "\"3482f01e9bf207a437a4b621c91339ad\"")
        }
        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"https://gateway.marvel.com/v1/public/\"")
            buildConfigField("String", "API_KEY", "\"b7e14bab409c70a5c4e7c2b319c09d7b\"")
            buildConfigField("String", "TS", "\"1682982412\"")
            buildConfigField("String", "HASH", "\"3482f01e9bf207a437a4b621c91339ad\"")
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
    koin()
    kotlin()
    coroutines()
    retrofit2()
    timber()
}