import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.androidxDesign() {
    implementation("androidx.core:core-ktx:${Versions.coreKtx}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}")
    implementation("com.google.android.material:material:${Versions.material}")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
}

fun DependencyHandler.androidxNavigation() {
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.navigation}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.navigation}")
}

fun DependencyHandler.koin() {
    implementation("io.insert-koin:koin-android:${Versions.koin}")
}

fun DependencyHandler.gson() {
    implementation("com.google.code.gson:gson:${Versions.gson}")
}

fun DependencyHandler.kotlin() {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}")
}

fun DependencyHandler.coroutines() {
    val version = Versions.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$version")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$version")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
}

fun DependencyHandler.glide() {
    val version = Versions.glide
    kapt("com.github.bumptech.glide:compiler:$version")
    implementation("com.github.bumptech.glide:glide:$version")
}

fun DependencyHandler.retrofit2() {
    val version = Versions.retrofit
    implementation("com.squareup.retrofit2:retrofit:$version")
    implementation("com.squareup.retrofit2:converter-gson:$version")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
}

fun DependencyHandler.androidxLifecycle() {
    val version = Versions.lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$version")
    implementation("androidx.lifecycle:lifecycle-common-java8:$version")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
}

fun DependencyHandler.timber() {
    implementation("com.jakewharton.timber:timber:5.0.1")
}

fun DependencyHandler.lottie() {
    implementation("com.airbnb.android:lottie:5.2.0")
}

fun DependencyHandler.testLibraries() {
    listOf(
        "junit:junit:4.13.2",
        "com.google.truth:truth:1.1.3",
        "io.mockk:mockk-android:${Versions.mockk}",
        "io.mockk:mockk-agent:${Versions.mockk}"
    ).forEach { dependency ->
        testImplementation(dependency)
    }
    androidTestUtil("androidx.test:orchestrator:${Versions.testOrchestrator}")
}