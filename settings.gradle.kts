buildCache {
  local {
    directory = File(rootDir, "build-cache")
    removeUnusedEntriesAfterDays = 30
  }
}

pluginManagement {
  includeBuild("build-src")
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
  }
}

dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "one-list"
include(":app")
include(":core:model")
include(":core:network")

include(":core:ui")

include(":data:todo:api")
include(":data:todo:impl")
include(":data:todo:fakeImpl")
include(":data:user:api")
include(":data:user:impl")
include(":data:user:fakeImpl")

include(":domain:login")
include(":domain:todo")

include(":feature:login")
include(":feature:todo")

