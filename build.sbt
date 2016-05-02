name := "RestTest"

version := "1.0"

scalaVersion := "2.11.8"
val akkaV = "2.4.2"
val sprayV = "1.3.3"
val slickV = "3.1.1"



libraryDependencies ++= Seq(
  "io.spray"            %%  "spray-can"     % sprayV,
  "io.spray"            %%  "spray-routing" % sprayV,
  "io.spray"            %%  "spray-http"    % sprayV,
  "io.spray"            %%  "spray-testkit" % sprayV   % "test",
  "com.typesafe.akka"   %% "akka-actor"     % akkaV,
  "com.typesafe.akka"   %% "akka-slf4j"     % akkaV,
  "com.typesafe.slick"  %% "slick"          % slickV,
//  "org.slf4j"           % "slf4j-nop"       % "1.6.4", // need this according to http://slick.lightbend.com/doc/3.1.1/gettingstarted.html
  "mysql"               % "mysql-connector-java" % "5.1.38",
  "net.liftweb"         %% "lift-json"      % "2.6+",
  "ch.qos.logback"      % "logback-classic" % "1.1.7"
)

resolvers ++= Seq(
  "Spray repository" at "http://repo.spray.io",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)