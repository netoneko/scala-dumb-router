name := "xwp-template"

organization := "com.earldouglas"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.0"

seq(webSettings :_*)

libraryDependencies += "org.mortbay.jetty" % "jetty" % "6.1.22" % "container"

libraryDependencies += "javax.servlet" % "servlet-api" % "2.5" % "provided"

libraryDependencies += "org.specs2" % "specs2_2.10" % "2.1.1" % "test"

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "releases"  at "http://oss.sonatype.org/content/repositories/releases")
