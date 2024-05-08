plugins {
    id("plugins.android.library")
    id("plugins.android.hilt")
}


dependencies {
    implementation(project(":core:model"))

    implementation(project(":data:user:api"))
    implementation(project(":data:todo:api"))

    implementation(libs.kotlinx.coroutines.android)
}