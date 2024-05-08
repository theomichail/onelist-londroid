import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.capitalone.mobile.onelist.configureFlavors
import com.capitalone.mobile.onelist.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ApplicationPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.application")
        apply("org.jetbrains.kotlin.android")
      }
      extensions.configure<BaseAppModuleExtension> {
        configureKotlinAndroid(this)
        defaultConfig {
          applicationId = "com.capitalone.mobile.onelist"
          versionCode = 1
          versionName = "1.0"

          targetSdk = 32

          testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        configureFlavors(this)

        buildFeatures {
          buildConfig = true
        }
      }
    }
  }

}