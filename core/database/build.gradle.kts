plugins {
    id("shopapp.android.library")
    id("shopapp.android.hilt")
    id("shopapp.android.room")
}

android {
    namespace = "com.example.shopapp.core.database"
}

dependencies {
    implementation(project(":core:domain"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
}
