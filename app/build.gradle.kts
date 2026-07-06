plugins {
    id("shopapp.android.application")
    id("shopapp.android.hilt")
}

android {
    namespace = "com.example.shopapp"

    defaultConfig {
        applicationId = "com.example.shopapp"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    // Core Android
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.9.3")

    // Compose BOM
    implementation(platform("androidx.compose:compose-bom:2024.12.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.5")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Feature modules
    implementation(project(":feature:login"))
    implementation(project(":feature:home"))
    implementation(project(":feature:productdetails"))
    implementation(project(":feature:individualproduct"))
    implementation(project(":feature:account"))
    implementation(project(":feature:cart"))

    // Core modules
    implementation(project(":core:data"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.12.01"))
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Material Icons Extended
    implementation("androidx.compose.material:material-icons-extended")

// Coil Compose
    implementation("io.coil-kt:coil-compose:2.7.0")
}