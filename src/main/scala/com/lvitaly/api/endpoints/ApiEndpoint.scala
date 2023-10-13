package com.lvitaly.api
package endpoints

import sttp.capabilities.zio.ZioStreams
import sttp.tapir.EndpointInput
import sttp.tapir.ztapir.*

private[endpoints] type ApiEndpoint = ZServerEndpoint[AppEnv, ZioStreams]

private[endpoints] val api = "api": EndpointInput[Unit]

private val apis: List[ApiEndpoint] = v1.endpoints

val all: List[ApiEndpoint] = apis ++ docs :+ metrics 
