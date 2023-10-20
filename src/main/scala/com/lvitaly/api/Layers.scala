package com.lvitaly.api

import service.*
import model.AppConfig
import zio.config.typesafe.TypesafeConfigProvider
import zio.{Runtime, TaskLayer, ZLayer}
import zio.http.Server
import zio.logging.backend.SLF4J

type AppEnv = AppConfig with Server with AuthorizationService

private def defaults =
  Runtime.removeDefaultLoggers >>> SLF4J.slf4j >>>
    Runtime.setConfigProvider(TypesafeConfigProvider.fromResourcePath()) // replace default env and props config providers with Typesafe (Lightbend) config provider

val appEnv: TaskLayer[AppEnv] =
  defaults >>> ZLayer.make[AppEnv](
    ConfigService.live,
    AuthorizationService.live,
    server.live,
  )