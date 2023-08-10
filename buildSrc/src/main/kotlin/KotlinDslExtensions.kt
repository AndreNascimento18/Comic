import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.`implementation`(dependencyNotation: String): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.`kapt`(dependency: String) : Dependency? =
    add("kapt", dependency)

fun DependencyHandler.`testImplementation`(dependency: String): Dependency? =
    add("testImplementation", dependency)

fun DependencyHandler.`androidTestUtil`(dependency: String): Dependency? =
    add("androidTestUtil", dependency)