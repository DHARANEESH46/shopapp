plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("javax.inject:javax.inject:1")
    implementation("androidx.paging:paging-common:3.4.2")
}