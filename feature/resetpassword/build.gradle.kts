plugins {
    id("shopapp.android.feature")
}

android {
    namespace = "com.example.shopapp.feature.resetpassword"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))
    implementation("androidx.compose.material:material-icons-extended:1.7.6")
}