package com.lvitaly.api.model

import zio.json.*

sealed trait ApiError

object ApiError:
  given decoder[T <: ApiError]: JsonDecoder[T] = DeriveJsonDecoder.gen[ApiError].asInstanceOf[JsonDecoder[T]]
  given encoder[T <: ApiError]: JsonEncoder[T] = DeriveJsonEncoder.gen[ApiError].asInstanceOf[JsonEncoder[T]]

final case class AuthorizationError(msg: String) extends ApiError
