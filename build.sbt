ThisBuild / scalaVersion     := "3.3.3"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "dev.nathanknox"
ThisBuild / organizationName := "dev.nathanknox"

val zioVersion       = "2.1.0-RC5"
val zioSchemaVersion = "1.1.1"

lazy val root = (project in file("."))
  .settings(
    name := "functional-wine",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio"                   % zioVersion,
      "dev.zio" %% "zio-streams"           % zioVersion,
      "dev.zio" %% "zio-test"              % zioVersion % Test,
      "dev.zio" %% "zio-test-sbt"          % zioVersion % Test,
      "dev.zio" %% "zio-schema"            % zioSchemaVersion,
      "dev.zio" %% "zio-schema-json"       % zioSchemaVersion,
      "dev.zio" %% "zio-schema-derivation" % zioSchemaVersion,
      "dev.zio" %% "zio-schema-zio-test"   % zioSchemaVersion,
      "dev.zio" %% "zio-prelude"           % "1.0.0-RC24",
      "dev.zio" %% "zio-json"              % "0.6.2"
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
