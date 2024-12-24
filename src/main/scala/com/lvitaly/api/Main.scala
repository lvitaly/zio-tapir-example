package com.lvitaly.api

import zio.*
import zio.config.typesafe.TypesafeConfigProvider
import zio.http.Server
import zio.logging.backend.SLF4J

object Main extends ZIOAppDefault with ZIOAppRuntime:

  override val bootstrap: ZLayer[ZIOAppArgs, Any, Any] =
    slf4jLogging >>> typesafeConfig

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    server.serve.provideSomeLayer[Scope](appEnv)


trait ZIOAppRuntime:
  
  protected val slf4jLogging: ULayer[Unit] =
    Runtime.removeDefaultLoggers >>> SLF4J.slf4j
  
  // replace default env and props config providers with Typesafe (Lightbend) config provider
  protected val typesafeConfig: ULayer[Unit] =
    Runtime.setConfigProvider(TypesafeConfigProvider.fromResourcePath())
  