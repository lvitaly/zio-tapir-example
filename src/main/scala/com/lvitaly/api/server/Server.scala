package com.lvitaly.api
package server

import model.AppConfig
import sttp.tapir.server.ziohttp.{ZioHttpInterpreter, ZioHttpServerOptions}
import zio.*
import zio.http.{HttpApp, Server}

private val options: ZioHttpServerOptions[AppEnv] =
  ZioHttpServerOptions.customiseInterceptors
    .metricsInterceptor(endpoints.prometheusMetrics.metricsInterceptor())
    .options

private val app: HttpApp[AppEnv, Throwable] = ZioHttpInterpreter(options).toHttp(endpoints.all)

val live: RLayer[AppConfig, Server] = ZLayer(ZIO.serviceWith[AppConfig](_.server)) >>> Server.live

val serve: URIO[AppEnv, Unit] =
  for {
    port <- Server.install(app.withDefaultErrorResponse)
    _    <- ZIO.logInfo(s"Go to http://localhost:$port/docs for Swagger UI")
    _    <- ZIO.never
  } yield ()
