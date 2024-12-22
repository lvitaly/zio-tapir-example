package com.lvitaly.api

import zio.*
import zio.config.typesafe.TypesafeConfigProvider
import zio.http.Server
import zio.logging.backend.SLF4J

object Main extends ZIOAppDefault:
  
  private val slf4jLogging =
    Runtime.removeDefaultLoggers >>> SLF4J.slf4j

  // replace default env and props config providers with Typesafe (Lightbend) config provider
  private val typesafeConfig =
    Runtime.setConfigProvider(TypesafeConfigProvider.fromResourcePath())

  override val bootstrap: ZLayer[ZIOAppArgs, Any, Any] =
    slf4jLogging >>> typesafeConfig

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    server.serve.provideSomeLayer[Scope](appEnv)
