plugins {
    id("plugins.android.library")
    id("plugins.android.hilt")
    id("kotlinx-serialization")
}
dependencies {
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.okhttp.logging)
    api(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}
