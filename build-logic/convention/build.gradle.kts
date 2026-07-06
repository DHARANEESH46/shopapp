import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.shopapp.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "shopapp.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "shopapp.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "shopapp.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidRoom") {
            id = "shopapp.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("androidFeature") {
            id = "shopapp.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("jetbrainsKotlinJvm") {
            id = "shopapp.jetbrains.kotlin.jvm"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}