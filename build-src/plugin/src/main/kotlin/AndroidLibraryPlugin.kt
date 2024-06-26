import com.android.build.gradle.LibraryExtension
import com.capitalone.mobile.onelist.configureFlavors
import com.capitalone.mobile.onelist.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.library")
        apply("org.jetbrains.kotlin.android")
      }

      extensions.configure<LibraryExtension> {
        configureKotlinAndroid(this)
        defaultConfig.targetSdk = 32
        configureFlavors(this)
        buildFeatures {
          buildConfig = true
        }
      }

      val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
      dependencies {
        configurations.configureEach {
          resolutionStrategy {
            force(libs.findLibrary("junit4").get())
            // Temporary workaround for https://issuetracker.google.com/174733673
            force("org.objenesis:objenesis:2.6")
          }
        }
      }
    }
  }

}
