plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = AppConfig.applicationId
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.appVersionCode
        versionName = AppConfig.appVersionName
        testInstrumentationRunner = AppConfig.androidJUnitRunner
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
        dataBinding = true
    }
    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }
    lint {
        checkReleaseBuilds = false
        abortOnError = false
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
    flavorDimensions += "brand"
    productFlavors {
        create("brandBlue") {
            dimension = "brand"
        }
        create("brandGreen") {
            dimension = "brand"
        }
    }
    kotlin {
        jvmToolchain(11)
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
    implementation(project(":domain"))
    implementation(project(":data"))
    koin()
    androidxDesign()
    androidxNavigation()
    gson()
    kotlin()
    coroutines()
    glide()
    timber()
    retrofit2()
    androidxLifecycle()
    testLibraries()
}