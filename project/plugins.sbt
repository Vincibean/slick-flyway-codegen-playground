resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers += "Flyway" at "https://flywaydb.org/repo"

addSbtPlugin("com.github.tototoshi" % "sbt-slick-codegen" % "1.3.0")

addSbtPlugin("io.github.davidmweber" % "flyway-sbt" % "5.2.0")

libraryDependencies += "com.h2database" % "h2" % "1.4.197"