import sbt._
import Keys._
import spray.revolver.RevolverPlugin._
import com.typesafe.sbt.SbtNativePackager._

object MyBuild extends Build {
  import Dependencies._

  lazy val buildSettings = Defaults.defaultSettings ++ Revolver.settings ++
     packageArchetype.java_application ++
     Seq(
        scalaVersion := "2.11.6",
        resolvers += Resolver.sonatypeRepo("snapshots"),
        resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
        libraryDependencies ++= Seq(
          http4sDSL,
          http4sBlaze,
          rhoSwagger,
          http4s_jackson,
          logbackClassic 
        )
    )

  lazy val myProject = Project(
    id = "my-project",
    base = file("."),
    settings = buildSettings :+ (version := Dependencies.http4sVersion)
  )


  object Dependencies {

    val http4sVersion = "0.8.2"
    val rhoVersion = "0.5.0"

    lazy val rhoSwagger     = "org.http4s"     %% "rho-swagger"           % rhoVersion
    lazy val http4sDSL      = "org.http4s"     %% "http4s-dsl"            % http4sVersion
    lazy val http4sBlaze    = "org.http4s"     %% "http4s-blazeserver"    % http4sVersion
    lazy val http4s_jackson = "org.http4s"     %% "http4s-json4s-jackson" % http4sVersion

    lazy val logbackClassic = "ch.qos.logback" % "logback-classic"        % "1.1.2"
  }
  
}
