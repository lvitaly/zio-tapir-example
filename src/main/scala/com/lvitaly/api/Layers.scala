package com.lvitaly.api

import com.lvitaly.api.service.*
import com.lvitaly.api.model.AppConfig
import zio.{Runtime, TaskLayer, ZLayer}
import zio.http.Server
import zio.logging.backend.SLF4J

object Layers:
  type AppEnv = AppConfig with Server with AuthorizationService

  val appEnv: TaskLayer[AppEnv] =
    Runtime.removeDefaultLoggers >>> SLF4J.slf4j >>> ZLayer.make[AppEnv](
      ConfigService.live,
      AuthorizationService.live,
      server.live,
    )