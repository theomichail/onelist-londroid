import com.android.build.api.dsl.ApplicationExtension
import com.capitalone.mobile.onelist.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class ApplicationComposePlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply("com.android.application")
      val extension = extensions.getByType<ApplicationExtension>()
      configureAndroidCompose(extension)
    }
  }

}