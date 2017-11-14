name := "AdvancedScala"

version := "1.0"

scalaVersion := "2.12.4"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

libraryDependencies += "com.github.mpilquist" %% "simulacrum" % "0.11.0"