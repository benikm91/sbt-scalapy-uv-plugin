ThisBuild / version := "0.1.0"
ThisBuild / organization := "ch.contrafactus"
ThisBuild / versionScheme := Some("early-semver")

ThisBuild / homepage := Some(
  url("https://github.com/benikm91/sbt-uv-auto-sync")
)
ThisBuild / licenses += (
  "Apache-2.0",
  url("http://www.apache.org/licenses/LICENSE-2.0")
)
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/benikm91/sbt-uv-auto-sync"),
    "git@github.com:benikm91/sbt-uv-auto-sync.git"
  )
)
ThisBuild / developers := List(
  Developer(
    "benikm91",
    "benikm91",
    "benikm91@gmail.com",
    url("https://github.com/benikm91")
  )
)

lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(
    name := "sbt-uv-auto-sync",
    publishMavenStyle := true,
    publishTo := {
      if (isSnapshot.value)
        Some(
          "Central Portal Snapshots" at "https://central.sonatype.com/repository/maven-snapshots/"
        )
      else
        Some(
          "Central Portal Releases" at "https://central.sonatype.com/api/v1/publisher/upload?publishingType=automatic"
        )
    }
  )
