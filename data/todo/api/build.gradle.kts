plugins {
    id("plugins.android.library")
    id("plugins.android.hilt")
    id("kotlinx-serialization")
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:network"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
}
