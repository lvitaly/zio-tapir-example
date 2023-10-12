package com.lvitaly.api.endpoints

import com.lvitaly.api.model.{ApiError, AuthorizationError}
import sttp.model.StatusCode
import sttp.tapir.generic.auto.*
import sttp.tapir.json.zio.*
import sttp.tapir.ztapir.{oneOf, oneOfDefaultVariant, oneOfVariant}
import sttp.tapir.{Endpoint, PublicEndpoint}

private[endpoints] trait ApiErrorCodec:
  extension [S, I, A, R](endpoint: Endpoint[S, I, Unit, A, R])
    def errorJsonOut: Endpoint[S, I, ApiError, A, R] =
      endpoint.errorOut(
        oneOf[ApiError](
          oneOfVariant(StatusCode.Unauthorized, jsonBody[AuthorizationError]),
          oneOfDefaultVariant(jsonBody[ApiError])
        )
      )
