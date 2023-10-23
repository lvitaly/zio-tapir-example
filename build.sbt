val tapirVersion = "1.8.2"
val zioVersion   = "2.0.18"

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
  "dev.zio" %% "zio-logging"         % "2.1.14",
  "dev.zio" %% "zio-logging-slf4j"   % "2.1.14",
  "dev.zio" %% "zio-config"          % "4.0.0-RC16",
  "dev.zio" %% "zio-config-magnolia" % "4.0.0-RC16",
  "dev.zio" %% "zio-config-typesafe" % "4.0.0-RC16",
  "dev.zio" %% "zio-test"            % zioVersion % Test,
  "dev.zio" %% "zio-test-sbt"        % zioVersion % Test
)

lazy val basicKit = Seq(
  "ch.qos.logback" % "logback-classic" % "1.4.11"
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
