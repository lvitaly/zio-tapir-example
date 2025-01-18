package com.lvitaly.api.model

import zio.json.*

sealed trait ApiError derives JsonDecoder, JsonEncoder

final case class AuthorizationError(msg: String) extends ApiError
final case class RepositoryError(msg: String)    extends ApiError
final case class UnexpectedError(msg: String)    extends ApiError
