val tapirVersion      = "1.9.5"
val zioVersion        = "2.0.20"
val zioConfigVersion  = "4.0.0-RC16"
val zioLoggingVersion = "2.1.16"

lazy val rootProject = (project in file(".")).settings(
  Seq(
    name           := "zio-tapir-example",
    version        := "0.1.0-SNAPSHOT",
    organization   := "com.lvitaly.api",
    scalaVersion   := "3.3.1",
    libraryDependencies ++= tapirKit ++ zioKit ++ basicKit,
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )
)

lazy val tapirKit = Seq(
  "com.softwaremill.sttp.tapir"   %% "tapir-zio-http-server"    % tapirVersion,
  "com.softwaremill.sttp.tapir"   %% "tapir-prometheus-metrics" % tapirVersion,
  "com.softwaremill.sttp.tapir"   %% "tapir-swagger-ui-bundle"  % tapirVersion,
  "com.softwaremill.sttp.tapir"   %% "tapir-json-zio"           % tapirVersion,
  "com.softwaremill.sttp.tapir"   %% "tapir-sttp-stub-server"   % tapirVersion % Test,
  "com.softwaremill.sttp.client3" %% "zio-json"                 % "3.9.0"      % Test
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

lazy val basicKit = Seq(
  "ch.qos.logback" % "logback-classic" % "1.4.12"
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
