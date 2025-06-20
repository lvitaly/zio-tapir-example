val tapirVersion      = "1.11.33"
val sttpVersion       = "4.0.8"
val zioVersion        = "2.1.19"
val zioConfigVersion  = "4.0.4"
val zioLoggingVersion = "2.5.0"
val h2Version         = "2.3.232"
val hikariCPVersion   = "6.3.0"
val magnumVersion     = "2.0.0-M2"

lazy val rootProject = (project in file("."))
  .settings(
    name           := "zio-tapir-example",
    version        := "0.1.0-SNAPSHOT",
    organization   := "com.lvitaly.api",
    scalaVersion   := "3.3.6",
    libraryDependencies ++= tapirKit ++ zioKit ++ jdbcKit ++ basicKit,
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework")),
    Test / fork    := true,
    revolverSettings
  )

lazy val tapirKit = Seq(
  "com.softwaremill.sttp.tapir"   %% "tapir-zio-http-server"    % tapirVersion,
  "com.softwaremill.sttp.tapir"   %% "tapir-prometheus-metrics" % tapirVersion,
  "com.softwaremill.sttp.tapir"   %% "tapir-swagger-ui-bundle"  % tapirVersion,
  "com.softwaremill.sttp.tapir"   %% "tapir-json-zio"           % tapirVersion,
  "com.softwaremill.sttp.tapir"   %% "tapir-sttp-stub4-server"  % tapirVersion % Test,
  "com.softwaremill.sttp.client4" %% "zio-json"                 % sttpVersion  % Test
)

lazy val zioKit = Seq(
  "dev.zio" %% "zio"                 % zioVersion,
  "dev.zio" %% "zio-streams"         % zioVersion,
  "dev.zio" %% "zio-logging"         % zioLoggingVersion,
  "dev.zio" %% "zio-logging-slf4j"   % zioLoggingVersion,
  "dev.zio" %% "zio-config"          % zioConfigVersion,
  "dev.zio" %% "zio-config-magnolia" % zioConfigVersion,
  "dev.zio" %% "zio-config-typesafe" % zioConfigVersion,
  "dev.zio" %% "zio-test"            % zioVersion % Test,
  "dev.zio" %% "zio-test-sbt"        % zioVersion % Test
)

lazy val jdbcKit = Seq(
  "com.h2database"   % "h2"        % h2Version,
  "com.zaxxer"       % "HikariCP"  % hikariCPVersion,
  "com.augustnagro" %% "magnumzio" % magnumVersion
)

lazy val basicKit = Seq(
  "ch.qos.logback" % "logback-classic" % "1.5.18"
)

lazy val revolverSettings = Revolver.settings ++ Seq(
  reStart / mainClass := Some("com.lvitaly.api.Main"),
  reStart / javaOptions ++= Seq(
    "-Xmx1g",
    "-Xms1g",
    "-Xss8m"
//    "-Duser.timezone=UTC",
  ),
  reStart / envVars   := Map(),
  Revolver.enableDebugging(port = 5005, suspend = false)
)
