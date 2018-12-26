lazy val databaseUrl = "jdbc:h2:file:./target/DB"
lazy val databaseUser = "SA"
lazy val databasePassword = ""

lazy val flyway = (project in file("flyway"))
  .enablePlugins(FlywayPlugin)
  .settings(
    scalaVersion := "2.12.8",
    flywayUrl := databaseUrl,
    flywayUser := databaseUser,
    flywayPassword := databasePassword
  )

lazy val root = (project in file("."))
  .enablePlugins(CodegenPlugin)
  .settings(
    scalaVersion := "2.12.8",
    name := "slick-flyway-codegen-playground",
    version := "0.1",
    libraryDependencies ++= Seq(
      ("com.typesafe.slick" %% "slick" % "3.2.3").exclude("org.slf4j", "slf4j-api"),
      ("com.typesafe.slick" %% "slick-hikaricp" % "3.2.3").exclude("org.slf4j", "slf4j-api"),
      "com.h2database" % "h2" % "1.4.197",
      "org.slf4j" % "slf4j-api" % "1.7.25",
      ("ch.qos.logback" % "logback-classic" % "1.2.3").exclude("org.slf4j", "slf4j-api"),
      "org.specs2" %% "specs2-core" % "4.3.6" % Test
    ),
    fork in Test := true,
    javaOptions in Test ++= Seq(
      "-Xmx1G",
      "-Xms512M",
      "-Xmx1024M",
      "-Xss2M",
      "-XX:MaxMetaspaceSize=1024M"
    ),
    slickCodegenDatabaseUrl := databaseUrl,
    slickCodegenDatabaseUser := databaseUser,
    slickCodegenDatabasePassword := databasePassword,
    slickCodegenDriver := slick.jdbc.H2Profile,
    slickCodegenJdbcDriver := "org.h2.Driver",
    slickCodegenOutputPackage := "org.vincibean.slick.flyway.codegen.playground",
    slickCodegenExcludedTables := Seq("schema_version"),
    sourceGenerators in Compile += (slickCodegen in Compile).taskValue
  )
  .dependsOn(flyway)
  .aggregate(flyway)