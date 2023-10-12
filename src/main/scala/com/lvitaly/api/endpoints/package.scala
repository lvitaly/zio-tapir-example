package com.lvitaly.api

import Layers.AppEnv
import sttp.capabilities.zio.ZioStreams
import sttp.tapir.EndpointInput
import sttp.tapir.ztapir.*

package object endpoints extends ApiDocs with ApiMetrics with ApiSecurity with ApiErrorCodec:

  private[endpoints] type AppEndpoint = ZServerEndpoint[AppEnv, ZioStreams]

  private[endpoints] val api = "api": EndpointInput[Unit]

  protected val apis: List[AppEndpoint] = v1.endpoints

  val all: List[AppEndpoint] = apis ++ docs :+ metrics
