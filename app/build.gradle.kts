plugins {
  id("plugins.android.application")
  id("plugins.android.application.compose")
  id("plugins.android.hilt")
}

android {

  namespace = "com.capitalone.mobile.onelist"

  buildTypes {

    val debug by getting {
      applicationIdSuffix = ".dev"
    }

    val release by getting {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  packagingOptions {
    resources {
      excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
  }
  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }
}


dependencies {

  //Domain module dependencies
  implementation(project(":core:model"))
  implementation(project(":core:ui"))

  implementation(project(":feature:login"))
  implementation(project(":feature:todo"))

  fakeImplementation(project(":data:user:fakeImpl"))
  interceptorImplementation(project(":data:user:impl"))
  mockImplementation(project(":data:user:impl"))
  devImplementation(project(":data:user:impl"))
  prodImplementation(project(":data:user:impl"))

  fakeImplementation(project(":data:todo:fakeImpl"))
  interceptorImplementation(project(":data:todo:impl"))
  mockImplementation(project(":data:todo:impl"))
  devImplementation(project(":data:todo:impl"))
  prodImplementation(project(":data:todo:impl"))

  implementation(libs.androidx.profileinstaller)
}
