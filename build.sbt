name := "AkkaSwagger"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += Resolver.sonatypeRepo("public")
resolvers += Resolver.sonatypeRepo("snapshots")
resolvers += DefaultMavenRepository

val akkaHttpVersion = "2.4.11"

libraryDependencies ++= Seq(
  "com.github.swagger-akka-http" %% "swagger-akka-http" % "0.8.1" excludeAll(ExclusionRule(organization = "com.typesafe.akka")),
  "ch.megard" %% "akka-http-cors" % "0.1.10" excludeAll( ExclusionRule(organization = "com.typesafe.akka")),
  "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion
)
