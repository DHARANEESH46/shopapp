plugins {
    id("shopapp.android.library")
    id("shopapp.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.shopapp.core.network"
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-opt-in=kotlinx.serialization.ExperimentalSerializationApi")
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
}
