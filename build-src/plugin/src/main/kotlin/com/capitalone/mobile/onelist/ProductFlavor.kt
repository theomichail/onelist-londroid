package com.capitalone.mobile.onelist

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.Project

enum class FlavorDimension {
  type
}

enum class Flavor (
  val dimension : FlavorDimension,
  val applicationIdSuffix : String,
  val baseUrl: String
) {
  fake(FlavorDimension.type, ".fake", ""),
  interceptor(FlavorDimension.type, ".interceptor", "http://doesntmatter.com"),
  mock(FlavorDimension.type, ".mock", "http://localhost:8899"),
  dev(FlavorDimension.type, ".dev", "https://dev.example.com"),
  prod(FlavorDimension.type, ".prod", "https://prod.example.com")
}

fun Project.configureFlavors(
  commonExtension: CommonExtension<*, *, *, *>,
  flavorConfigurationBlock: ProductFlavor.(flavor: Flavor) -> Unit = {}
) {
  commonExtension.apply {
    flavorDimensions += FlavorDimension.type.name
    productFlavors {
      Flavor.values().forEach{
        create(it.name) {
          dimension = it.dimension.name
          flavorConfigurationBlock(this, it)
          if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
          this.applicationIdSuffix = it.applicationIdSuffix
          }
          buildConfigField("String", "BASE_URL", "\"${it.baseUrl}\"")
        }
      }
    }
  }
}