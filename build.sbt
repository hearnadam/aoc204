Global / onChangedBuildSource := ReloadOnSourceChanges
ThisBuild / version           := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion      := "3.5.2"

val kyoVersion = "0.14.1"

lazy val root = (project in file(".")).settings(
  name := "advent-of-code-2024",
  scalacOptions ++= Seq(
    "-encoding",
    "utf8",
    "-feature",
    "-unchecked",
    "-explain",
    "-deprecation",
    "-new-syntax",
    "-language:implicitConversions",
    "-Wconf:msg=(discarded.*value|pure.*statement):error",
    "-Wconf:msg=unused:silent",
    "-Wconf:msg=.*scalatest.*Assertion.*:silent",
    "-Xmax-inlines:100",
    "-Wunused:all",
    "-release:21",
  ),
  libraryDependencies ++= Seq(
    "io.getkyo"                     %% "kyo-core"        % kyoVersion,
    "io.getkyo"                     %% "kyo-combinators" % kyoVersion,
    "io.getkyo"                     %% "kyo-stats-otel"  % kyoVersion,
  ),
  scalafmtOnCompile := true,
)