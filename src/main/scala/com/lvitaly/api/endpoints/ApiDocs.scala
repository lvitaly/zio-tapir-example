package com.lvitaly.api.endpoints

import sttp.tapir.*
import sttp.tapir.swagger.bundle.SwaggerInterpreter

private lazy val docs: List[ApiEndpoint] =
  SwaggerInterpreter().fromServerEndpoints(apis, "tapir-example", "1.0.0")
