import AssemblyKeys._

name := "Etl"
version := "1.0"
scalaVersion := "2.10.1"
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "0.10.1.1"
libraryDependencies += "org.apache.kafka" % "kafka_2.10" % "0.10.1.1"
libraryDependencies += "org.apache.spark" % "spark-streaming-kafka_2.10" % "1.6.3"
libraryDependencies += "org.apache.spark" % "spark-core_2.10" % "2.1.0"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.10" % "2.1.0"
//libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.0"


assemblySettings

mergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
  case "log4j.properties"                                  => MergeStrategy.discard
  case m if m.toLowerCase.startsWith("meta-inf/services/") => MergeStrategy.filterDistinctLines
  case "reference.conf"                                    => MergeStrategy.concat
  case _                                                   => MergeStrategy.first
}


