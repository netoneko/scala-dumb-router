name := "xwp-template"

organization := "com.earldouglas"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.1"

seq(webSettings :_*)

libraryDependencies ++= {
  Seq(
    "org.eclipse.jetty"       %  "jetty-webapp" % "8.1.8.v20121106"     % "container,test",
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "compile,container,test" artifacts Artifact("javax.servlet", "jar", "jar")
  )
}
