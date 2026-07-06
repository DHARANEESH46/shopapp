pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "shopapp"

// App
include(":app")

// Core modules
include(":core:network")
include(":core:database")
include(":core:data")
include(":core:domain")
include(":core:designsystem")

// Feature modules
include(":feature:login")
include(":feature:home")
include(":feature:productdetails")
include(":feature:individualproduct")
include(":feature:account")
include(":feature:cart")