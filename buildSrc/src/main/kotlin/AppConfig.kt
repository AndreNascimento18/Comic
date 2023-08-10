import org.gradle.api.JavaVersion

object AppConfig {

    const val minSdkVersion = 21
    const val compileSdkVersion = 33
    const val targetSdkVersion = 33

    const val applicationId = "com.example.comics"
    const val appVersionName = "1.0"
    const val appVersionCode = 1

    const val androidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val apiVersion = "1.8"
    const val languageVersion = "1.8"
    const val jvmTarget = "11"
    private val javaVersion = JavaVersion.VERSION_11
    val sourceCompatibility = javaVersion
    val targetCompatibility = javaVersion
}