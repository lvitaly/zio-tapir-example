package com.lvitaly.api

import com.lvitaly.api.model.AppConfig
import com.lvitaly.api.service.*
import zio.http.Server
import zio.{TaskLayer, ZLayer}

type AppEnv = AppConfig with Server with AuthorizationService

val appEnv: TaskLayer[AppEnv] =
  ZLayer.make[AppEnv](
    ConfigService.live,
    AuthorizationService.live,
    server.live,
  )