name := "ScalaLearn"

version := "1.0"

scalaVersion := "2.12.5"
scalacOptions += "-feature"
scalacOptions += "-Ypartial-unification"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.4",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.4" % Test,
  "com.typesafe.akka" %% "akka-stream" % "2.5.4",
  "org.scalafx" %% "scalafx" % "8.0.102-R11",
  "org.typelevel" %% "cats-core" % "1.0.1"

)
libraryDependencies += "co.fs2" %% "fs2-core" % "0.10.1"
    