import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeaturePlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply {
        apply("plugins.android.library")
        apply("plugins.android.hilt")
      }
      extensions.configure<LibraryExtension> {
        defaultConfig {
          testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
      }

      dependencies {
        add("implementation", project(":core:model"))
        add("implementation", project(":core:ui"))
      }
    }
  }
}