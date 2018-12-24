lazy val databaseUrl = "jdbc:h2:file:./target/DB"
lazy val databaseUser = "SA"
lazy val databasePassword = ""

lazy val flyway = (project in file("flyway"))
  .enablePlugins(FlywayPlugin)
  .settings(
    scalaVersion := "2.12.8",
    flywayUrl := databaseUrl,
    flywayUser := databaseUser,
    flywayPassword := databasePassword,
    flywayLocations += "db/migration"
  )

lazy val root = (project in file("."))
  .enablePlugins(CodegenPlugin)
  .settings(
    scalaVersion := "2.12.8",
    name := "slick-flyway-codegen-playground",
    version := "0.1",
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.2.3",
      "com.h2database" % "h2" % "1.4.186",
      "org.specs2" %% "specs2-core" % "4.3.6" % Test
    ),
    slickCodegenDatabaseUrl := databaseUrl,
    slickCodegenDatabaseUser := databaseUser,
    slickCodegenDatabasePassword := databasePassword,
    slickCodegenDriver := slick.jdbc.H2Profile,
    slickCodegenJdbcDriver := "org.h2.Driver",
    slickCodegenOutputPackage := "models",
    slickCodegenExcludedTables := Seq("schema_version"),
    sourceGenerators in Compile += (slickCodegen in Compile).taskValue,
    slickCodegenOutputDir := (sourceManaged in Compile).value / "a"
  )
  .dependsOn(flyway)
  .aggregate(flyway)