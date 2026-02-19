package dimwit.installation

import sbt.*
import sbt.Keys.*
import scala.sys.process.*

object SbtUvAutoSync extends AutoPlugin {

  override def trigger = allRequirements

  // Use standard Scala 2.12 curly braces
  object autoImport {
    val dimwitPythonSync = taskKey[Unit]("Syncs python dependencies using uv for Dimwit")
    val dimwitUseUv = settingKey[Boolean]("Whether to use uv for dependency management")
  }

  import autoImport.*

  override lazy val projectSettings: Seq[Setting[_]] = Seq(
    dimwitUseUv := true,
    dimwitPythonSync := {
      val log = streams.value.log
      if (dimwitUseUv.value) {
        log.info("[dimwit] Syncing Python dependencies...")
        if (Process("uv sync").! != 0) {
          sys.error("uv sync failed. Ensure uv is installed: https://docs.astral.sh/uv/")
        }
      }
    },
    (Compile / compile) := ((Compile / compile) dependsOn dimwitPythonSync).value
  )
}