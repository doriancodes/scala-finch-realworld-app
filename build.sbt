name := "scala-finch-realworld-app"

version := "0.1"

scalaVersion := "2.12.8"

lazy val finchVersion = "0.27.0"
lazy val circeVersion = "0.9.0"
lazy val twitterServerVersion = "19.1.0"
lazy val doobieVersion = "0.6.0"

scalacOptions += "-Ypartial-unification" // 2.11.9+

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finchx-core" % finchVersion,
  "com.github.finagle" %% "finchx-circe" % finchVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "com.twitter" %% "twitter-server" % twitterServerVersion,
  "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test",
  "org.tpolecat" %% "doobie-core" % doobieVersion,
  "org.tpolecat" %% "doobie-postgres" % doobieVersion,
  "org.tpolecat" %% "doobie-specs2" % doobieVersion,
  "org.tpolecat" %% "doobie-scalatest" % doobieVersion,
  "com.pauldijou" %% "jwt-circe" % "2.1.0",
  "org.slf4j" % "slf4j-simple" % "1.7.12"
)

addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
)