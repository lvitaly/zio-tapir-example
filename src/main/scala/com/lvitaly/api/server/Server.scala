package com.lvitaly.api
package server

import sttp.tapir.server.ziohttp.{ZioHttpInterpreter, ZioHttpServerOptions}
import zio.*
import zio.http.{Response, Routes, Server}

private val options: ZioHttpServerOptions[AppEnv] =
  ZioHttpServerOptions.customiseInterceptors
    .metricsInterceptor(endpoints.prometheusMetrics.metricsInterceptor())
    .options

private val app: Routes[AppEnv, Response] = ZioHttpInterpreter(options).toHttp(endpoints.all)

private val config: Layer[Config.Error, Server.Config] = ZLayer(ZIO.config(Server.Config.config.nested("server")))

val live: TaskLayer[Server] = config >>> Server.live

val serve: URIO[AppEnv, Unit] =
  for {
    port <- Server.install(app)
    _    <- ZIO.logInfo(s"Go to http://localhost:$port/docs for Swagger UI")
    _    <- ZIO.never
  } yield ()
