name := "puhelin-luettelo-app"

version := "0.1"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % "2.6.3",
  "org.eclipse.jetty" % "jetty-webapp" % "9.2.30.v20200428",
  "org.scalikejdbc" %% "scalikejdbc" % "3.4.2",
  "org.scalikejdbc" %% "scalikejdbc-config" % "3.4.2"
)