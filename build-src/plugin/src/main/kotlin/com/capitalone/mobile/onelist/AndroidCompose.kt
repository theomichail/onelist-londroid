package com.capitalone.mobile.onelist

import com.android.build.api.dsl.CommonExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import java.io.File

internal fun Project.configureAndroidCompose(
  commonExtension: CommonExtension<*, *, *, *>,
) {
//  val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
  val libs = extensions.getByType(LibrariesForLibs::class.java)

  commonExtension.apply {
    buildFeatures {
      compose = true
    }

    composeOptions {
      kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }

    kotlinOptions {
      freeCompilerArgs = freeCompilerArgs + buildComposeMetricsParameters()
    }
    
    dependencies {
      val bom = libs.androidx.compose.bom
      add("implementation", platform(bom))
      add("androidTestImplementation", platform(bom))

      add("androidTestImplementation", libs.androidx.navigation.testing)
      add("implementation", libs.androidx.activity.compose)
      add("implementation", libs.androidx.compose.runtime)
      add("implementation", libs.androidx.appcompat)
      add("implementation", libs.androidx.core.ktx)
      add("implementation", libs.androidx.window.manager)
      add("implementation", libs.accompanist.systemuicontroller)
      add("implementation", libs.androidx.compose.material3.windowSizeClass)
      add("implementation", libs.androidx.hilt.navigation.compose)
      add("implementation", libs.androidx.lifecycle.runtimeCompose)
      add("implementation", libs.androidx.lifecycle.viewModelCompose)
      add("implementation", libs.kotlinx.coroutines.android)
      add("implementation", libs.androidx.navigation.compose)
      add("androidTestImplementation", libs.androidx.navigation.testing)

      add("api", libs.androidx.compose.foundation)
      add("api", libs.androidx.compose.foundation.layout)
      add("api", libs.androidx.compose.material.iconsExtended)
      add("api", libs.androidx.compose.material3)
      add("debugApi", libs.androidx.compose.ui.tooling)
      add("api", libs.androidx.compose.ui.tooling.preview)
      add("api", libs.androidx.compose.ui.util)
    }
  }
}

private fun Project.buildComposeMetricsParameters(): List<String> {
  val metricParameters = mutableListOf<String>()
  val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
  val enableMetrics = (enableMetricsProvider.orNull == "true")
  if (enableMetrics) {
    val metricsFolder = File(project.buildDir, "compose-metrics")
    metricParameters.add("-P")
    metricParameters.add(
      "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath
    )
  }

  val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
  val enableReports = (enableReportsProvider.orNull == "true")
  if (enableReports) {
    val reportsFolder = File(project.buildDir, "compose-reports")
    metricParameters.add("-P")
    metricParameters.add(
      "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + reportsFolder.absolutePath
    )
  }
  return metricParameters.toList()
}