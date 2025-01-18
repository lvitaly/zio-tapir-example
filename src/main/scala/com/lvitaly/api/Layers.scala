package com.lvitaly.api

import com.lvitaly.api.model.AppConfig
import zio.http.Server
import zio.{ZLayer, TaskLayer}

type AppEnv = AppConfig 
  & Server 
  & repository.Repository 
  & service.Service

val appEnv: TaskLayer[AppEnv] =
  ZLayer.make[AppEnv](
    config.live,
    repository.live,
    service.live,
    server.live,
    db.live
  )
