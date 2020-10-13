name := "GPT2Service"

version := "1.0"

lazy val `gpt2service` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(jdbc, ehcache, ws, specs2 % Test, guice,
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,

  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,

  // for tensorflow4java
  "org.tensorflow" % "tensorflow" % "1.15.0",
  "org.tensorflow" % "proto" % "1.15.0",
  "org.tensorflow" % "libtensorflow_jni" % "1.15.0")

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

      