plugins {
    id("shopapp.android.library")
    id("shopapp.android.hilt")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.shopapp.core.designsystem"
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2024.12.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("io.coil-kt:coil-compose:2.7.0")
}
