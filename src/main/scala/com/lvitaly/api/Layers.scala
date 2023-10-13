package com.lvitaly.api

import service.*
import model.AppConfig
import zio.{Runtime, TaskLayer, ZLayer}
import zio.http.Server
import zio.logging.backend.SLF4J

type AppEnv = AppConfig with Server with AuthorizationService

val appEnv: TaskLayer[AppEnv] =
  Runtime.removeDefaultLoggers >>> SLF4J.slf4j >>> ZLayer.make[AppEnv](
    ConfigService.live,
    AuthorizationService.live,
    server.live,
  )