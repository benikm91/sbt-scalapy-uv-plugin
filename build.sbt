ThisBuild / version := "1.0.0"
ThisBuild / organization := "ch.contrafactus"

lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(
    name := "sbt-uv-auto-sync"
  )
