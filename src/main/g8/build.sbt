val SCALATEST_VERSION = "3.0.3"
val PROJECT_HOMEPAGE_URL = "https://github.com/dafutils/$name;format="norm"$"
val BINTRAY_USER = System.getenv("BINTRAY_USER")
val BINTRAY_PASSWORD = System.getenv("BINTRAY_PASS")

lazy val versionSettings = Seq(
  //  The 'version' setting is not set on purpose: its value is generated automatically by the sbt-dynver plugin
  //  based on the git tag/sha. Here we're just tacking on the maven-compatible snapshot suffix if needed
  version := {
    val snapshotVersion = dynverGitDescribeOutput.value
      .filter(gitVersion => gitVersion.isSnapshot())
      .map(output => output.version + "-SNAPSHOT")

    snapshotVersion.getOrElse(version.value)
  }
)

lazy val publicationSettings = Seq(
  publishTo := {
    if (isSnapshot.value)
      Some(s"Artifactory Realm" at "https://oss.jfrog.org/artifactory/oss-snapshot-local;build.timestamp=" + new java.util.Date().getTime)
    else
      publishTo.value //Here we are assuming that the bintray-sbt plugin does its magic and the publish settings are set to
    //point to Bintray
  },
  credentials := {
    if (isSnapshot.value) {
      Seq(
        Credentials(
          realm = "Artifactory Realm",
          host = "oss.jfrog.org",
          userName = BINTRAY_USER,
          passwd = BINTRAY_PASSWORD
        )
      )
    }
    else
      credentials.value //Similar
  },
  publishArtifact in Test := false,
  bintrayReleaseOnPublish := !isSnapshot.value
)


lazy val projectMetadataSettings = Seq(
  licenses += "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"),
  homepage := Some(url(PROJECT_HOMEPAGE_URL)),
  scmInfo := Some(
    ScmInfo(
      browseUrl = url(PROJECT_HOMEPAGE_URL),
      connection = "scm:git:git@github.com:dafutils/$name;format="norm"$.git"
    )
  ),
  developers := List(
    Developer(
      id = "$dev_id$",
      name = "$dev_name$",
      email = "$dev_email$",
      url = url("https://github.com/dafutils")
    )
  )
)

lazy val $name;format="camel"$ = (project in file("."))
  .configs(IntegrationTest)
  .settings(Defaults.itSettings)
  .settings(projectMetadataSettings)
  .settings(versionSettings)
  .settings(publicationSettings)
  .settings(
    scalaVersion := "2.12.2",

    organization := "$package$",

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
      "org.mockito" % "mockito-all" % "$mockito_version$" % "it,test"
    )
  )
