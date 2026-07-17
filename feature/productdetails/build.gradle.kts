plugins {
    id("shopapp.android.feature")
}

android {
    namespace = "com.example.shopapp.feature.productdetails"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.paging:paging-common:3.4.2")
    implementation("androidx.paging:paging-compose:3.4.2")

}
