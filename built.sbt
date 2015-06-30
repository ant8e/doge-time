import scalariform.formatter.preferences._

name :="doge-time"

version := "0.1"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-feature", "-deprecation", "-unchecked")

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test"

scalariformSettings

ScalariformKeys.preferences := FormattingPreferences()
  .setPreference(AlignParameters, true)
  .setPreference(RewriteArrowSymbols, true)
  .setPreference(PreserveDanglingCloseParenthesis, false)

