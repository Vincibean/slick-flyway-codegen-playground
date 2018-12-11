resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

// settings for slick-codegen and sbt-flyway

addSbtPlugin("com.github.tototoshi" % "sbt-slick-codegen" % "1.3.0")

resolvers += "Flyway" at "https://flywaydb.org/repo"

addSbtPlugin("io.github.davidmweber" % "flyway-sbt" % "5.2.0")

libraryDependencies += "com.h2database" % "h2" % "1.4.186"