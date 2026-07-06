plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    // Needed for @Inject annotation in use cases
    implementation("javax.inject:javax.inject:1")
}