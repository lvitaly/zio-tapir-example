package com.lvitaly.api

import zio.*
import zio.config.typesafe.TypesafeConfigProvider
import zio.http.Server
import zio.logging.backend.SLF4J

object Main extends ZIOAppDefault:

  override val bootstrap: ZLayer[ZIOAppArgs, Any, Any] =
    Runtime.removeDefaultLoggers >>> SLF4J.slf4j >>>
      Runtime.setConfigProvider(TypesafeConfigProvider.fromResourcePath()) // replace default env and props config providers with Typesafe (Lightbend) config provider

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    server.serve.provideSomeLayer[Scope](appEnv)
