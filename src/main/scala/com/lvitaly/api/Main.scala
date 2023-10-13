package com.lvitaly.api

import zio.http.Server
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}

object Main extends ZIOAppDefault:

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    server.serve.provideSomeLayer[Scope](appEnv)
