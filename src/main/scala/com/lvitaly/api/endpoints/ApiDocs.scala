package com.lvitaly.api.endpoints

import sttp.tapir.*
import sttp.tapir.swagger.bundle.SwaggerInterpreter

trait ApiDocs:
  protected val apis: List[AppEndpoint]

  protected lazy val docs: List[AppEndpoint] =
    SwaggerInterpreter().fromServerEndpoints(apis, "tapir-example", "1.0.0")
