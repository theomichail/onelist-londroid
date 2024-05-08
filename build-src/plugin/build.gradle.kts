plugins {
  `kotlin-dsl`
}

group = "com.capitalone.mobile.onelist.build_src"

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)

  implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
  plugins {
    register("androidApplicationCompose") {
      id = "plugins.android.application.compose"
      implementationClass = "ApplicationComposePlugin"
    }
    register("androidApplication") {
      id = "plugins.android.application"
      implementationClass = "ApplicationPlugin"
    }
    register("androidLibraryCompose") {
      id = "plugins.android.library.compose"
      implementationClass = "AndroidLibraryComposePlugin"
    }
    register("androidLibrary") {
      id = "plugins.android.library"
      implementationClass = "AndroidLibraryPlugin"
    }
    register("androidFeature") {
      id = "plugins.android.feature"
      implementationClass = "AndroidFeaturePlugin"
    }
    register("androidHilt") {
      id = "plugins.android.hilt"
      implementationClass = "AndroidHiltPlugin"
    }
  }
}
