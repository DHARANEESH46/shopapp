plugins {
    id("shopapp.android.feature")
}

android {
    namespace = "com.example.shopapp.feature.wishlist"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))
    implementation("io.coil-kt:coil-compose:2.7.0")
}
