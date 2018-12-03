import Dependencies._

resolvers in ThisBuild ++= Seq[Resolver](
  Resolver.bintrayRepo("hajile", "maven"),
  Resolver.jcenterRepo,
  Resolver.sonatypeRepo("snapshots")
)

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.7",
      version      := "0.1.0-SNAPSHOT",
      mainClass in assembly := Some("index.mem.test.Main"),
      assemblyJarName in assembly := "index-mem-test.jar",
      fork in run := true
    )),
    name := "index-mem-test",
    libraryDependencies ++= Seq(atlasCore, scalaTest % Test)
  )
