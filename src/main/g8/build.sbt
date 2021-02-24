val versionSettings = Seq(
  //  The 'version' setting is not set on purpose: its value is generated automatically by the sbt-dynver plugin
  //  based on the git tag/sha. Here we're just tacking on the maven-compatible snapshot suffix if needed
  dynverSonatypeSnapshots in ThisBuild := true,
  version in ThisBuild ~= (_.replace('+', '_')),
  dynver in ThisBuild ~= (_.replace('+', '_'))
)

lazy val $name;format="camel"$ = (project in file("."))
  .configs(IntegrationTest)
  .settings(Defaults.itSettings)
  .settings(versionSettings)
  .settings(
    scalaVersion := "$scala_version$",

    organization := "$organization$",

    name := "$name$",
    
    libraryDependencies ++= Seq(
      //Application config
      "com.typesafe" % "config" % "$typesafe_config_version$",

      //Logging
      "ch.qos.logback" % "logback-classic" % "$logback_verison$",
      "com.typesafe.scala-logging" %% "scala-logging" % "$scala_logging_version$",

      //Test
      "org.scalactic" %% "scalactic" % "$scalatest_version$",
      "org.scalatest" %% "scalatest" % "$scalatest_version$" % "it,test",
      "org.scalatestplus" %% "mockito-3-4" % "3.2.2.0" % "it,test",
      "org.mockito" % "mockito-core" % "$mockito_version$" % "it,test"
    )
  )
