plugins {
    id("shopapp.android.library")
    id("shopapp.android.hilt")
}

android {
    namespace = "com.example.shopapp.core.data"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation("androidx.paging:paging-common:3.4.2")
}
