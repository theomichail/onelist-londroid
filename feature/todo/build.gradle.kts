
plugins {
    id("plugins.android.feature")
    id("plugins.android.library.compose")
}

dependencies {
    implementation(project(":domain:todo"))
}