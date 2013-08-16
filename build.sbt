name := "xwp-template"

organization := "com.earldouglas"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.0"

seq(webSettings :_*)

libraryDependencies ++= Seq(
    "org.eclipse.jetty.orbit"   % "javax.servlet"   % "3.0.0.v201112011016" % "provided"    artifacts Artifact("javax.servlet", "jar", "jar"),
    "org.eclipse.jetty"         % "jetty-webapp"    % "9.0.4.v20130625"     % "container"
)

libraryDependencies += "org.specs2" % "specs2_2.10" % "2.1.1" % "test"

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "releases"  at "http://oss.sonatype.org/content/repositories/releases")
